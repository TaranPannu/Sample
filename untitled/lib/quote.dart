import 'package:flutter/material.dart';
import 'package:untitled/list.dart';
import 'package:untitled/screen2.dart';

class quote extends StatelessWidget {

  list qu;
var delete;
  quote({required this.qu, required this.delete});
  @override
  Widget build(BuildContext context) {

    return Padding(

      padding: const EdgeInsets.all(20.0),
      child: Column(
        children: [
          Card(

            margin: EdgeInsets.fromLTRB(20.0, 50.0, 16.0, 16.0)
            ,child: Text("Hello ${qu.name}",style: TextStyle(
          ),),

          ),
          ElevatedButton(onPressed: delete, child: Icon(Icons.delete,color: Colors.white,)),
          ElevatedButton(onPressed: (){
            Navigator.pushNamed(context, "/screen2",arguments: {
              'id':'value'
            });
          }, child: Icon(Icons.navigate_next,color: Colors.white,)),

        ],
      ),
    );;
  }
}
