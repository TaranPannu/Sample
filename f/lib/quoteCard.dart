import 'package:f/quotes.dart';
import 'package:flutter/material.dart';
class quoteCard extends StatelessWidget {
quotes e;
var delete;
quoteCard({required this.e, required this.delete});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Column(
        children: [
          Text("${e.quote}   Hello ${e.auth}"),
          SizedBox(height: 15.0),
          Text("data"),
          ElevatedButton(onPressed: delete, child: Text("Click"))
        ],
      ),
    );
  }
}
