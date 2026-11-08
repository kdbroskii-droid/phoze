# Phoze — Liquid Glass

Phoze is an iOS-inspired interface prototype, not an operating system. It is a responsive web app/PWA designed to run in a browser and can be added to the iPhone Home Screen from Safari.

## Current prototype
- iOS-style rounded-square app icons with custom vector artwork
- Liquid Glass surfaces, blur, translucency and reflections
- Lock screen with live clock/date and a two-panel hinge-style unlock animation
- Home screen, dock and tappable app previews
- Customisation for accent colour, glass opacity/blur, corner roundness, wallpaper, icon size, labels, widgets, clock format and animation toggles
- Preferences saved in browser local storage

## Run
Open `index.html` in a modern browser, or serve this folder from a static web host. For iPhone, open the hosted page in Safari and use Share → Add to Home Screen.

## Storage and rendering
Phoze stores user preferences in local browser storage. The browser still uses RAM and the device GPU to display the interface and animate it; storage cannot replace working memory or graphics hardware.

## Current scope
This is the first working interface foundation. The app previews are UI placeholders, not replacements for Apple's system apps or native iOS features.
