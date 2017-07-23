# Single Page App + Android App

Until I know how I add this to an automatic build, here is everything I did:

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

To build the android app run

`ng build --target=production --output-path android/www`

`cordova run android --device`

## Signing

Copy the properties file and the keyfile into `platforms/android`