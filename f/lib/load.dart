import 'package:flutter/material.dart';
class load extends StatefulWidget {

  @override
  State<load> createState() => _loadState();
}

class _loadState extends State<load> {
  void initState(){
    super.initState();
    print("choosen");
  }
  @override
  Widget build(BuildContext context) {

    var data=ModalRoute.of(context)!.settings.arguments as Map;
    return Scaffold(
      appBar: AppBar(title: Text("HEADLINE $data"),),

    );
  }
}
