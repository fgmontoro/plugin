var exec = require('cordova/exec');

exports.getBTStatus = function(success, error) {
    exec(success, error, "plugin_bluetooth", "getBTStatus");
};

exports.bindBT = function(success, error) {
    exec(success, error, "plugin_bluetooth", "bindBT");
};

exports.unBindBT = function(success, error) {
    exec(success, error, "plugin_bluetooth", "unBindBT");
};

exports.start = function(success, error) {
    exec(success, error, "plugin_bluetooth", "start");
};
