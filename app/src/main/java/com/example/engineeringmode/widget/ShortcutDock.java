package com.example.engineeringmode.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author ljt
 * Date: 3/29/21
 * Time: 10:25 AM
 * Description:
 */
public class ShortcutDock extends LinearLayout {

    private static final int INVALID_SCREEN = -1;

    private int mScrollingSpeed = 600;

    private int mScrollingBounce = 50;

    private boolean mFirstLayout = true;

    private static final int SNAP_VELOCITY = 300;

    private int mCurrentScreen;

    private int mNextScreen = INVALID_SCREEN;

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;

    private float mLastMotionX;

    private float mLastMotionY;

    private final static int TOUCH_STATE_REST = 0;

    private final static int TOUCH_STATE_SCROLLING = 1;

    private int mTouchState = TOUCH_STATE_REST;

    private int mMaximumVelocity;

    private int mTouchSlop;

    public ShortcutDock(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHapticFeedbackEnabled(false);
        initWorkspace();
        requestFocus();
    }
    private void initWorkspace() {
        mScroller = new Scroller(getContext());
        mCurrentScreen = 1;
        final ViewConfiguration configuration = ViewConfiguration
                .get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }
    int getCurrentScreen() {
        return mCurrentScreen;
    }
    protected Parcelable onSaveInstanceState() {
        final SavedState state = new SavedState(super.onSaveInstanceState());
        state.currentScreen = mCurrentScreen;
        return state;
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.currentScreen != -1) {
            mCurrentScreen = savedState.currentScreen;
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException(
                    "ShortcutDock can only be used in EXACTLY mode.");
        }
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException(
                    "ShortcutDock can only be used in EXACTLY mode.");
        }
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
        if (mFirstLayout) {
            setHorizontalScrollBarEnabled(false);
            scrollTo(mCurrentScreen * width, 0);
            mScroller.startScroll(0, 0, mCurrentScreen * width, 0, 0);
            setHorizontalScrollBarEnabled(false);
            mFirstLayout = false;
        }
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        int childLeft = 0;
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                final int childWidth = child.getMeasuredWidth();
                child.layout(childLeft, 0, childLeft + childWidth, child
                        .getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        } else if (mNextScreen != INVALID_SCREEN) {
            mCurrentScreen = Math.max(0, Math.min(mNextScreen,
                    getChildCount() - 1));
            mNextScreen = INVALID_SCREEN;
        }
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if ((action == MotionEvent.ACTION_MOVE)
                && (mTouchState != TOUCH_STATE_REST)) {
            return true;
        }
        final float x = ev.getX();
        final float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                final int xDiff = (int) Math.abs(x - mLastMotionX);
                final int yDiff = (int) Math.abs(y - mLastMotionY);
                final int touchSlop = mTouchSlop;
                boolean xMoved = xDiff > touchSlop;
                boolean yMoved = yDiff > touchSlop;
                if (xMoved || yMoved) {
                    if (xDiff > yDiff) {
                        mTouchState = TOUCH_STATE_SCROLLING;
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;
                mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST
                        : TOUCH_STATE_SCROLLING;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        return mTouchState != TOUCH_STATE_REST;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);
        final int action = ev.getAction();
        final float x = ev.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastMotionX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                final int deltaX = (int) (mLastMotionX - x);
                mLastMotionX = x;
                int tScrollX = getScrollX();
                if (deltaX < 0) {
                    if (tScrollX > -mScrollingBounce) {
                        scrollBy(Math.min(deltaX, mScrollingBounce), 0);
                    }
                } else if (deltaX > 0) {
                    final int availableToScroll = getChildAt(getChildCount() - 1)
                            .getRight()
                            - tScrollX - getWidth() + mScrollingBounce;
                    if (availableToScroll > 0) {
                        scrollBy(deltaX, 0);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int velocityX = (int) velocityTracker.getXVelocity();
                if (velocityX > SNAP_VELOCITY && mCurrentScreen > 0) {
                    snapToScreen(mCurrentScreen - 1);
                } else if (velocityX < -SNAP_VELOCITY
                        && mCurrentScreen < getChildCount() - 1) {
                    snapToScreen(mCurrentScreen + 1);
                } else {
                    snapToDestination();
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                mTouchState = TOUCH_STATE_REST;
                break;
            case MotionEvent.ACTION_CANCEL:
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        return true;
    }
    private void snapToDestination() {
        final int screenWidth = getWidth();
        final int whichScreen = (getScrollX() + (screenWidth / 2))
                / screenWidth;
        snapToScreen(whichScreen);
    }
    void snapToScreen(int whichScreen) {
        whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
        boolean changingScreens = whichScreen != mCurrentScreen;
        mNextScreen = whichScreen;
        View focusedChild = getFocusedChild();
        if (focusedChild != null && changingScreens
                && focusedChild == getChildAt(mCurrentScreen)) {
            focusedChild.clearFocus();
        }
        final int screenDelta = Math.abs(whichScreen - mCurrentScreen);
        int durationOffset = 1;
        if (screenDelta == 0) {
            durationOffset = 400;
        }
        final int duration = mScrollingSpeed + durationOffset;
        final int newX = whichScreen * getWidth();
        final int delta = newX - getScrollX();
        mScroller.startScroll(getScrollX(), 0, delta, 0, duration);
        invalidate();
    }
    public static class SavedState extends BaseSavedState {
        int currentScreen = -1;
        SavedState(Parcelable superState) {
            super(superState);
        }
        private SavedState(Parcel in) {
            super(in);
            currentScreen = in.readInt();
        }
        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(currentScreen);
        }
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
