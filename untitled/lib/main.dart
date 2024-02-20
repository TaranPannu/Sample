import 'package:flutter/material.dart';
import 'package:untitled/sc.dart';
import 'package:untitled/screen2.dart';
import 'package:untitled/test.dart';
import 'package:untitled/test1.dart';

void main() {
  runApp(MaterialApp(
  initialRoute: '/',
    routes: {
      '/': (context) => Test1(),
      '/screen2': (context) => screen2(),
      '/Test': (context) => Test(),
      '/Test1': (context) => Test1(),
      '/sc': (context) => sc(),
    },

  ));
}

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: SafeArea(
        child: Scaffold(
          appBar: AppBar(
            title: Text("Test"),
          ),
          body: Row(
            children: [
              Expanded(
                child: Container(
                  color: Colors.yellowAccent,
                  //  margin: EdgeInsets.fromLTRB(50.0, 50.0, 0.0, 0.0)
                  child: Text(
                    "hey",
                    style: TextStyle(fontSize: 50.0),
                  ),
                ),
              ),
              Container(
                color: Colors.green,
                child: Text(
                  "hey",
                  textScaleFactor: 5.0,
                ),
              ),
            ],
          ),
        ),
      ),
    );
    ;
  }
}
