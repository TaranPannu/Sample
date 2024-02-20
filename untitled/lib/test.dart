import 'package:flutter/material.dart';
import 'package:untitled/list.dart';
import 'package:untitled/quote.dart';

class Test extends StatefulWidget {

  @override
  State<Test> createState() {
    return _TestState();
  }
}

class _TestState extends State<Test> {
  int x=0;
  List m=[list(name:"Taran"),list(name: "name"),list(name: "Karan")];

  @override
  Widget build(BuildContext context) {
    return Scaffold(appBar: AppBar(title: Text("Title"),),
    backgroundColor: Colors.black,
    body: Padding(
      padding: const EdgeInsets.all(20.0),
      child: Column(

        children: m.map((e) {
          return quote(qu: e,delete: (){
            setState(() {
              m.remove(e);
            });
          },);
        }).toList()
      ),
    ),
    );
  }
}
