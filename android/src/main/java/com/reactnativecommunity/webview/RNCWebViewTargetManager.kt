package com.reactnativecommunity.webview

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerModule
import com.facebook.react.uimanager.annotations.ReactProp

/**
 * A lightweight display target for a WebView that already lives in [RNCWebViewMapManager]. Where RNCWebView
 * builds, configures, and owns a WebView, this only borrows one by its webViewKey and shows it. It never
 * re-points the WebChromeClient, never writes the viewId map or the shared map, so the RNCWebViewContainer that
 * built the WebView stays its owner: onMessage and load callbacks keep routing to that builder while this target
 * merely displays the view. On unmount it hands the WebView back to its temporary parent so it survives for the
 * next target rather than being torn down.
 */
class RNCWebViewTargetView(context: ThemedReactContext) : FrameLayout(context) {
  init {
    // React adds the WebView to us without measuring or laying it out (facebook/react-native#17968), so on its own
    // it keeps whatever size it had in the pool and leaves a blank gap. We fix that by laying out the CHILD to fill
    // us, at our top-left. It has to be the child, never the parent: React owns our own frame and has already placed
    // us below the channel header, so re-laying the parent out at (0,0) would yank the whole target up under the
    // header and leave a blank strip at the bottom where it no longer reaches.
    setOnHierarchyChangeListener(object : ViewGroup.OnHierarchyChangeListener {
      override fun onChildViewAdded(parent: View, child: View) {
        child.measure(
          View.MeasureSpec.makeMeasureSpec(parent.measuredWidth, View.MeasureSpec.EXACTLY),
          View.MeasureSpec.makeMeasureSpec(parent.measuredHeight, View.MeasureSpec.EXACTLY),
        )
        child.layout(0, 0, parent.measuredWidth, parent.measuredHeight)
      }

      override fun onChildViewRemoved(parent: View, child: View) {}
    })
  }

  var webViewKey: String? = null
    set(value) {
      field = value
      borrow()
    }
  var temporaryParentNodeTag: Int = 0

  // Borrow as soon as our builder registers the WebView, in case it mounted after us — the counterpart to the
  // borrow() we run on attach for the case where the builder was already there.
  private val registrationListener: (String) -> Unit = { key ->
    if (key == webViewKey) {
      borrow()
    }
  }

  private fun mappedWebView(): WebView? {
    val key = webViewKey ?: return null
    return RNCWebViewMapManager.rncWebViewMap[key]
  }

  fun borrow() {
    val webView = mappedWebView()
    if (webView == null) {
      return
    }
    if (webView.parent === this) {
      return
    }
    // Detach from wherever it currently lives — the builder, another target, or the temporary parent — by
    // reparenting. We deliberately leave the builder's ownership bookkeeping (viewId map, chrome client) alone;
    // we're only moving the view, not taking it over.
    (webView.parent as? ViewGroup)?.removeView(webView)
    addView(webView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    RNCWebViewMapManager.addRegistrationListener(registrationListener)
    // The builder may already have created the WebView; grab it now. If not, registrationListener catches it.
    borrow()
  }

  override fun onDetachedFromWindow() {
    RNCWebViewMapManager.removeRegistrationListener(registrationListener)
    super.onDetachedFromWindow()
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    // Our real size can arrive after the WebView was borrowed in (the onHierarchyChange pass may have run before
    // we were measured), so re-lay it out to our current bounds here to keep the guest filling us exactly.
    val webView = mappedWebView() ?: return
    if (webView.parent === this) {
      webView.measure(
        View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY),
      )
      webView.layout(0, 0, w, h)
    }
  }

  fun returnToHome() {
    val webView = mappedWebView() ?: return
    // Only return it if we're the one currently showing it — if another target already stole it, it owns the
    // hand-off now.
    if (webView.parent !== this) {
      return
    }
    removeView(webView)
    if (temporaryParentNodeTag == 0) {
      return
    }
    val uiManager = (context as ReactContext).getNativeModule(UIManagerModule::class.java) ?: return
    val home = uiManager.resolveView(temporaryParentNodeTag) as? ViewGroup ?: return
    home.addView(webView)
    // Resize to match the home, mirroring how RNCWebViewManager returns a view to its temporary parent.
    webView.measure(
      MeasureSpec.makeMeasureSpec(home.measuredWidth, MeasureSpec.EXACTLY),
      MeasureSpec.makeMeasureSpec(home.measuredHeight, MeasureSpec.EXACTLY)
    )
    webView.layout(0, 0, webView.measuredWidth, webView.measuredHeight)
  }
}

class RNCWebViewTargetManager : SimpleViewManager<RNCWebViewTargetView>() {
  override fun getName() = REACT_CLASS

  public override fun createViewInstance(reactContext: ThemedReactContext) = RNCWebViewTargetView(reactContext)

  @ReactProp(name = "webViewKey")
  fun setWebViewKey(view: RNCWebViewTargetView, webViewKey: String?) {
    view.webViewKey = webViewKey
  }

  @ReactProp(name = "temporaryParentNodeTag")
  fun setTemporaryParentNodeTag(view: RNCWebViewTargetView, tag: Int) {
    view.temporaryParentNodeTag = tag
  }

  override fun onDropViewInstance(view: RNCWebViewTargetView) {
    super.onDropViewInstance(view)
    view.returnToHome()
  }

  companion object {
    const val REACT_CLASS = "RNCWebViewTarget"
  }
}
