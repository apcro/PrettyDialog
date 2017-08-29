# PrettyDialog
PrettyDialog is a customizable equivalent of SCLAlertView in iOS.

Example is available in app module.
## Download
### Gradle:
Add the following to your project level build.gradle:

```
allprojects {
 repositories {
  ...
  maven { url "https://jitpack.io" }
 }
}
```
Add this to your app build.gradle:

```
dependencies {
 compile 'com.github.mjn1369:prettydialog:1.0.0'
}
```
## Usage
PrettyDialog extends Dialog class, so feel free to use its inherited functions.
**Note:** Default dialog has no title, message or any buttons. Just a close icon on top which you can dismiss the dialog by clicking on it.
### Simple Dialog, No Customization:
```
new PrettyDialog(this)
	.setTitle("PrettyDialog Title")
	.setMessage("PrettyDialog Message")
	.show();
```
#### Output:
![alt text](https://github.com/mjn1369/PrettyDialog/blob/master/Screenshots/simple.png "Simple Dialog, No Customization")