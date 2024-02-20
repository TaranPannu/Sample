import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';

class api extends StatefulWidget {

  @override
  State<api> createState() => _apiState();
}

class _apiState extends State<api> {
  void initState(){
    super.initState();
    getData();
  }
  void getData() async{
    var client=http.Client();
    var uri=Uri.parse("https://jsonplaceholder.typicode.com/todos/1");
    var response= await client.get(uri);
    Map data=jsonDecode(response.body);
    print(data);
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(appBar: AppBar(title: Text("TITLE"),
    ),
    body: Text("Body"),);
  }
}
