

import 'package:f/quoteCard.dart';
import 'package:f/quotes.dart';
import 'package:flutter/material.dart';

import 'API/api.dart';
import 'home.dart';
import 'load.dart';
import 'quotes.dart';

void main() {
  runApp( MyApp());
}
class MyApp extends StatefulWidget {

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  int i=0;
 List<quotes> quote=[
   quotes("quote", "auth"),
 quotes("quote1", "auth1")
 ];


  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      initialRoute:
      '/', //this going to be the initial route and will overwrite the "/"=>screen
      routes: {
        '/': (context) => home(),
        '/load': (context) => load(),
        '/api': (context) => api(),

      },
    );


  }
}
