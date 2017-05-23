var exec = require('cordova/exec');

exports.coolMethod = function(arg0, success, error) {
    exec(success, error, "plugin_bluetooth", "coolMethod", [arg0]);
};

exports.miMetodo = function(arg0, success, error) {
    exec(success, error, "plugin_bluetooth", "miMetodo", [arg0]);
};
