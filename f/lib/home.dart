import 'package:flutter/material.dart';
class home extends StatefulWidget {

  @override
  State<home> createState() => _homeState();
}

class _homeState extends State<home> {
  @override
  Widget build(BuildContext context) {
    int i=0;
    return Scaffold(

      backgroundColor: Colors.grey[500],
      appBar: AppBar(
        title: Text("Title"),
        centerTitle: true,

      ),
      body: Center(
          child: (
              Column(
                children: [
                  (Icon(Icons.airport_shuttle)),
                  ElevatedButton.icon(onPressed: (){
                    setState(() {
                      i++;
                      Navigator.pushNamed(context, '/api',arguments: {
                        "key":"value"
                      });
                    });
                  }, icon: Icon(Icons.access_alarm_rounded), label: Text("Click $i")
                  ),
                  Image.asset("assets/cant.png"),
                  Column(

                  )
                ],
              )
          )
      ),
    );
  }
}
