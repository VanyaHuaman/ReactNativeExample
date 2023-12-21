import React from 'react';
import {AppRegistry, StyleSheet, Text, View, SafeAreaView, Alert, NativeEventEmitter, NativeModules, DeviceEventManager} from 'react-native';

const HelloWorld = () => {
  return (
    <View style={styles.container}>
     <Text style={styles.hello}>Hello, World</Text>
    </View>
    );
};
const styles = StyleSheet.create({
 container: {
  flex: 1,
  justifyContent: 'center',
 },
 hello: {
  fontSize: 20,
  textAlign: 'center',
  margin: 10,
 },
});

const HelloComponent = () => {
 return (
   <View style={styles.container}>
    <Text style={styles.hello}>Hello, Component</Text>
   </View>
  );
};

const MessageComponent = (props) => {
 const androidValues = props
 const cr_number = androidValues.cr_number
 console.log('Props passed:', cr_number)
 return (
  <View style={styles.hello}>
   <Text style={styles.hello}>{cr_number}</Text>
   </View>
  );
};

AppRegistry.registerComponent(
 'ReactNativeExample',
 () => HelloWorld,
 );

AppRegistry.registerComponent(
 'HelloComponent',
 () => HelloComponent,
 );

AppRegistry.registerComponent(
 'MessageComponent',
 () => MessageComponent,
 );