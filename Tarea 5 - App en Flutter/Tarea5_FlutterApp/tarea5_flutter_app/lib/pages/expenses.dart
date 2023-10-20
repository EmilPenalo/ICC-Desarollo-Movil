import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:tarea5_flutter_app/models/category.dart';
import 'package:tarea5_flutter_app/pages/ui/bar_graph.dart';
import 'package:tarea5_flutter_app/util/date_util.dart';
import '../models/expense_item.dart';

class Expenses extends StatefulWidget {
  final List<Category> categories;
  final List<ExpenseItem> expenses;

  const Expenses({
    super.key,
    required this.categories,
    required this.expenses,
  });

  @override
  State<Expenses> createState() => _ExpensesState();
}

class _ExpensesState extends State<Expenses> {

  @override
  Widget build(BuildContext context) {
    widget.expenses.sort((a, b) => b.dateTime.compareTo(a.dateTime));

    return Scaffold(
        backgroundColor: Colors.grey[100],
        appBar: AppBar(
          backgroundColor: Colors.green,
          centerTitle: true,
          title: const Text('Gastos'),
          actions: [
            IconButton(
              icon: const Icon(Icons.add_rounded),
              tooltip: 'Nuevo Gasto',
              onPressed: () {
                newExpenseDialog();
              },
            ),
          ],
        ),
      body: Container(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: <Widget>[
            Container(
              margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
              height: 250,
                child: ExpensesBarChart(expenses: widget.expenses),
            ),
            Expanded(
              child: ListView.separated(
                itemCount: widget.expenses.length,
                itemBuilder: (context, index) {
                  final expense = widget.expenses[index];
                  bool isFirstItem = index == 0 || !isSameDay(widget.expenses[index - 1].dateTime, expense.dateTime);

                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: <Widget>[
                      if (isFirstItem)
                        Container(
                          color: Colors.green[50],
                          padding: const EdgeInsets.all(8),
                          child: Text(
                            dateToString(expense.dateTime),
                            style: TextStyle(
                              color: Colors.grey[600],
                              fontSize: 14,
                              fontStyle: FontStyle.italic,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ListTile(
                        title: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(expense.name,
                            style: const TextStyle(fontSize: 16, fontWeight: FontWeight.w500)),
                            const SizedBox(height: 8),
                          ],
                        ),
                        trailing: Text("\$${expense.amount}"),
                        subtitle: FittedBox(
                          fit: BoxFit.scaleDown,
                          alignment: Alignment.centerLeft,
                          child: Container(
                            padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 5),
                            decoration: BoxDecoration(
                              color: expense.category.backgroundColor,
                              borderRadius: BorderRadius.circular(12),
                            ),
                            child: Text(
                              expense.category.name,
                              style: TextStyle(color: Colors.grey[900]),
                            ),
                          ),
                        )
                      ),
                    ],
                  );
                },
                separatorBuilder: (context, index) => const Divider(),
              ),
            ),
          ],
        ),
      ),
    );
  }

  void addExpense(ExpenseItem item) {
    setState(() {
      widget.expenses.add(item);
    });
  }

  void newExpenseDialog() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        String name = "";
        String amount = "";
        Category? selectedCategory;

        return StatefulBuilder(
          builder: (context, setState) {
            return AlertDialog(
              title: const Text('Agregar Gasto'),
              content: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  TextField(
                    onChanged: (value) {
                      name = value;
                    },
                    decoration: const InputDecoration(labelText: 'Nombre'),
                  ),
                  TextField(
                    onChanged: (value) {
                      amount = value;
                    },
                    keyboardType: const TextInputType.numberWithOptions(decimal: true),
                    inputFormatters: <TextInputFormatter>[
                      FilteringTextInputFormatter.allow(RegExp(r'^\d+\.?\d{0,2}')),
                    ],
                    decoration: const InputDecoration(labelText: 'Cantidad'),
                  ),
                  SizedBox(
                    width: double.infinity,
                    child: Row(
                      children: [
                        Expanded(
                          child: DropdownButton<Category>(
                            padding: const EdgeInsets.fromLTRB(0, 16, 0, 0),
                            value: selectedCategory,
                            onChanged: (Category? newValue) {
                              setState(() {
                                selectedCategory = newValue;
                              });
                            },
                            hint: const Text('Categoria'),
                            underline: Container(),
                            items: widget.categories.map((Category category) {
                              return DropdownMenuItem<Category>(
                                value: category,
                                child: Row(
                                    children: [
                                      CircleAvatar(
                                        backgroundColor: category.backgroundColor,
                                        radius: 15,
                                      ),
                                      const SizedBox(width: 10),
                                      Text(category.name),
                                  ]
                                ),
                              );
                            }).toList(),
                          ),
                        ),
                      ],
                    ),
                  )
                ],
              ),
              actions: <Widget>[
                TextButton(
                  child: const Text("Agregar"),
                  onPressed: () {
                    if (name.isNotEmpty && amount.isNotEmpty && selectedCategory != null) {
                        final item = ExpenseItem(
                          name: name,
                          amount: amount,
                          dateTime: DateTime.now(),
                          category: selectedCategory!,
                        );

                        addExpense(item);

                      Navigator.of(context).pop();
                    }
                  },
                ),
              ],
            );
          },
        );
      },
    );
  }
}
