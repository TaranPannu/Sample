import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
class screen2 extends StatefulWidget {

  @override
  State<screen2> createState() => _screen2State();
}

class _screen2State extends State<screen2> {
  void initState(){
    super.initState();
    print("init state");
  }
  void gte() async{
var uri=Uri.parse("https://jsonplaceholder.typicode.com/todos/1");
var response=await http.Client().get(uri);
Map data=jsonDecode(response.body);
print(data['title']);

  }
  int x=0;
  late Map data;
  @override
  Widget build(BuildContext context) {
    print("Build");
     data=ModalRoute.of(context)!.settings.arguments as Map;

    return Scaffold(appBar: AppBar(title: Text("TITLE ${data['id']}"),

    ),
body: ElevatedButton(onPressed: (){
  setState(() {
    print("before");
gte();
print("after");
    x++;
  });
}, child: Text("Click $x"),),
    );
  }
}
