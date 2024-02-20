import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:untitled/list.dart';
import 'package:untitled/qu.dart';

class Test1 extends StatefulWidget {
  @override
  State<Test1> createState() => _Test1State();
}

class _Test1State extends State<Test1> {
  List quote = [list(name: "name"), list(name: "xxxx"), list(name: "aaaa")];
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.grey[300],
        body: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(15.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: quote
                  .map((e) => cardd(
                        name: e.name,
                        delete: () {
                          setState(() {
                            quote.remove(e);
                          });
                        },
                      ))
                  .toList(),
            ),
          ),
        ),
      ),
    );
  }
}
