import 'package:tarea5_flutter_app/models/category.dart';

class ExpenseItem {
   final String name;
   final String amount;
   final DateTime dateTime;
   final Category category;

   ExpenseItem({
     required this.name,
     required this.amount,
     required this.dateTime,
     required this.category,
   });
}