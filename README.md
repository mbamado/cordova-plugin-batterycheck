# cordova-plugin-batterycheck

Reads the battery level and charging state of an Android device once, on demand.

## Install

```bash
cordova plugin add https://github.com/mbamado/cordova-plugin-batterycheck.git#v1.0.0
```

For OutSystems, set the module's Extensibility Configurations to this repo, pinned to the tag. The tag must exist before the build.

## Usage

```javascript
cordova.plugins.BatteryCheck.getStatus(
    function (status) {
        // status.level: 0-100
        // status.isPlugged: true when the device is charging or on power
    },
    function (message) {
        // message: why the status could not be read
    }
);
```

## Platforms

- Android only. No iOS implementation.

## Permissions

- None. The plugin reads the sticky battery broadcast and does not register a receiver.

## Attribution

The `plugin.xml` layout and the `level` / `isPlugged` field names follow [cordova-plugin-battery-status](https://github.com/apache/cordova-plugin-battery-status) (Apache License 2.0). The code here is original and one-shot, not a copy of that plugin.

## Status

- Not yet run on a device.

## License

MIT (proposed). Confirm before publishing.
