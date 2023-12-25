import 'package:flutter/material.dart';

class cardd extends StatelessWidget {
  String name;
  var delete;
  cardd({required String this.name, this.delete});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.fromLTRB(16.0, 16.0, 16.0, 16.0),
      child: Column(
        children: [
          Text(
            "xx " + name,
          ),
          SizedBox(
            height: 15.0,
          ),
          Text("data"),
          ElevatedButton(onPressed: delete, child: Icon(Icons.abc_sharp)),
          ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, "/sc");
              },
              child: Icon(Icons.access_alarm_outlined))
        ],
      ),
    );
  }
}
