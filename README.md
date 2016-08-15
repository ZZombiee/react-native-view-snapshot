Take snapshots of React Native views (and their children) and write to device storage.

## Installation

```
% npm i --save react-native-view-snapshot
```

In XCode, in your project, add the .m and .h files to your Library folder.

## Usage

For a full example, build the included ViewSnapshotExample project.

This library exposes a single method, *saveSnapshotToPath*. Example usage:

```
var ViewSnapshotter = require('react-native-view-snapshot');

ViewSnapshotter.saveSnapshotToPath(React.findNodeHandle(this.refs.someView), somePath, (error, successfulWrite) => {
    if (successfulWrite) {
        this.setState({catSaved: true})
    } else {
      console.log(error)
    }
});
```

## Use cases

* storing performant copies of complex static views, such as a set of layered PNGs with alpha transparency
* sharing/uploading of snapshots specific view hierarchies, such as an image with a text overlay

##Android配置
1. Open up `android/app/src/main/java/[...]/MainActivity.java
  - Add `import com.viewsnapshotexample.ReactTestPackage;` to the imports at the top of the file
  - Add `new ReactTestPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:

	```
	include ':react-native-view-snapshot'
	project(':react-native-view-snapshot').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-view-snapshot/android')
    ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:

	```
    compile project(':react-native-view-snapshot')
	```	
4. 打开当前项目目录下的package.json文件
   - 更改`"react-native-view-snapshot": "git+https://github.com/ZZombiee/react-native-view-snapshot.git"`