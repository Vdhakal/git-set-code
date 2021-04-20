//package com.example.git_set_code.fragments;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.animation.AnimatorSet;
//import android.animation.ValueAnimator;
//
//import com.google.android.gms.maps.CameraUpdate;
//import com.here.sdk.core.GeoCoordinates;
//
//public class CameraAnimator {
//    public void moveTo(GeoCoordinates destination, double targetZoom) {
//        CameraUpdate targetCameraUpdate = createTargetCameraUpdate(destination, targetZoom);
//        createAnimation(targetCameraUpdate);
//        startAnimation(targetCameraUpdate);
//    }
//    private void createAnimation(CameraUpdate cameraUpdate) {
//        valueAnimatorList.clear();
//
//        // Interpolate current values for zoom, tilt, bearing, lat/lon to the desired new values.
//        ValueAnimator zoomValueAnimator = createAnimator(camera.getZoomLevel(), cameraUpdate.zoomLevel);
//        ValueAnimator tiltValueAnimator = createAnimator(camera.getTilt(), cameraUpdate.tilt);
//        ValueAnimator bearingValueAnimator = createAnimator(camera.getBearing(), cameraUpdate.bearing);
//        ValueAnimator latitudeValueAnimator = createAnimator(
//                camera.getTarget().latitude, cameraUpdate.target.latitude);
//        ValueAnimator longitudeValueAnimator = createAnimator(
//                camera.getTarget().longitude, cameraUpdate.target.longitude);
//
//        valueAnimatorList.add(zoomValueAnimator);
//        valueAnimatorList.add(tiltValueAnimator);
//        valueAnimatorList.add(bearingValueAnimator);
//        valueAnimatorList.add(latitudeValueAnimator);
//        valueAnimatorList.add(longitudeValueAnimator);
//
//        // Update all values together.
//        zoomValueAnimator.addUpdateListener(animation -> {
//            float zoom = (float) zoomValueAnimator.getAnimatedValue();
//            float tilt = (float) tiltValueAnimator.getAnimatedValue();
//            float bearing = (float) bearingValueAnimator.getAnimatedValue();
//            float latitude = (float) latitudeValueAnimator.getAnimatedValue();
//            float longitude = (float) longitudeValueAnimator.getAnimatedValue();
//
//            GeoCoordinates intermediateGeoCoordinates = new GeoCoordinates(latitude, longitude);
//            camera.updateCamera(new CameraUpdate(tilt, bearing, zoom, intermediateGeoCoordinates));
//        });
//    }
//    private ValueAnimator createAnimator(double from, double to) {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat((float) from, (float )to);
//        if (timeInterpolator != null) {
//            valueAnimator.setInterpolator(timeInterpolator);
//        }
//        return valueAnimator;
//    }
//    private void startAnimation(CameraUpdate cameraUpdate) {
//        if (animatorSet != null) {
//            animatorSet.cancel();
//        }
//
//        animatorSet = new AnimatorSet();
//        animatorSet.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                camera.updateCamera(cameraUpdate);
//            }
//        });
//
//        animatorSet.playTogether(valueAnimatorList);
//        animatorSet.setDuration(animationDurationInMillis);
//        animatorSet.start();
//    }
//}
