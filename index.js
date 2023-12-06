import React from 'react';
import {AppRegistry, StyleSheet, Text, View} from 'react-native';

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

AppRegistry.registerComponent(
 'ReactNativeExample',
 () => HelloWorld,
 );

AppRegistry.registerComponent(
 'HelloComponent',
 () => HelloComponent,
 );