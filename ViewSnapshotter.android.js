'use strict';

var React = require('react-native');
var {
  NativeModules
} = React;

var NativeViewSnapshotter = NativeModules.ViewSnapshotter;

var ViewSnapshotter = {
  saveSnapshotToPath: NativeViewSnapshotter.ViewSnapshotter
};

module.exports = ViewSnapshotter;

