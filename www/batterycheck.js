var exec = require('cordova/exec');

var BatteryCheck = {
    /**
     * Reads the battery status once.
     * @param {function({level: number, isPlugged: boolean})} success
     *   level is the charge percentage (0-100), isPlugged is true when on power.
     * @param {function(string)} fail  receives an error message.
     */
    getStatus: function (success, fail) {
        exec(success, fail, 'BatteryCheck', 'getStatus', []);
    }
};

module.exports = BatteryCheck;
