# TabletFrame
Quick & Dirty Android app for wall mounted tablets displaying Dashing dashboards for OpenHAB

This is a simple Android app using webView for displaying a dashing dashboard configured for home automation. The webView refreshes whenever the app gains focus, whether this is waking from sleep or on launch. The app is aware of touch interaction and refreshes after REFRESH_TIMER of inactivity. This resets the dashboard to the main page and ensures the display is always current. The app will take fullscreen after IMMERSIVE_TIMER.

## Motivation

Using an Android tablet for home automation control is funky sometimes. There's no easy way to launch a shortcut saved to the home screen the same way you can an app. With a few tasker actions, this works much more reliably.
My dashboard configuration is at https://github.com/ragingcomputer/openhab-dashboard

## Required Apps
[Android System WebView](https://play.google.com/store/apps/details?id=com.google.android.webview)
[Tasker](https://play.google.com/store/apps/details?id=net.dinglisch.android.taskerm)
[Secure Settings](https://play.google.com/store/apps/details?id=com.intangibleobject.securesettings.plugin)
[Motion Detector](https://play.google.com/store/apps/details?id=org.motion.detector)

## Installation
Using this requires instaling [Android Studio](https://developer.android.com/studio/index.html) to customize, build the project, then install to your Android tablet.

[![Installing TabletFrame](http://img.youtube.com/vi/rn-UsMa9kLM/0.jpg)](http://www.youtube.com/watch?v=rn-UsMa9kLM "Installing TabletFrame")

## Customization
Everything important happens in app/src/main/java/com/ragingcomputer/apps/tabletframe/MainActivity.java
**DASHBOARD_URL** - URL of the dashing instance. It's best if you copy/paste this.
**REFRESH_TIMER** - Time in milliseconds between page refresh. This timer is reset each time the screen is touched or the page reloads for other reasons.
**IMMERSIVE_TIMER** - Time in milliseconds after last touch the screen returns to fullscreen.

## License

MIT License

Copyright (c) 2016 Benjamin Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.