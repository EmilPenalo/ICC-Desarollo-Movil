import 'package:circular_bottom_navigation/tab_item.dart';
import 'package:flutter/material.dart';
import 'package:tarea5_flutter_app/models/expense_item.dart';
import 'package:tarea5_flutter_app/pages/categories.dart';
import 'package:circular_bottom_navigation/circular_bottom_navigation.dart';
import 'package:tarea5_flutter_app/models/category.dart';
import 'package:tarea5_flutter_app/pages/expenses.dart';

void main() {
  runApp(const ExpenseTracker());
}

class ExpenseTracker extends StatelessWidget {
  const ExpenseTracker({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: BottomNav(),
    );
  }
}

class BottomNav extends StatefulWidget {
  const BottomNav({super.key});

  @override
  State<BottomNav> createState() =>
      _BottomNavState();
}

class _BottomNavState extends State<BottomNav> {
  int _selectedIndex = 0;

  // Categorias por defecto de prueba
  List<Category> categories = [Category(name: 'Comida'), Category(name: 'Transporte')];
  List<ExpenseItem> expenses = [];
  List<Widget> _widgetTabs = [];

  List<TabItem> tabItems = List.of([
    TabItem(
      Icons.bar_chart_rounded,
      "Gastos",
      Colors.green,
      labelStyle: const TextStyle(
        fontWeight: FontWeight.bold,
      ),
    ),
    TabItem(
      // Icons.emoji_objects_rounded,
      Icons.grid_view_rounded,
      "Categorias",
      Colors.blue,
      labelStyle: const TextStyle(
        fontWeight: FontWeight.bold,
      ),
    ),
  ]);

  late CircularBottomNavigationController _navigationController;

  @override
  void initState() {
    super.initState();
    _navigationController = CircularBottomNavigationController(_selectedIndex);

    // Datos por defecto de prueba
    expenses.add(
        ExpenseItem(
            name: 'Peaje',
            amount: '100.00',
            dateTime: DateTime.now().subtract(const Duration(days: 1)),
            category: categories[1])
    );
    expenses.add(
        ExpenseItem(
            name: 'El Rinconcito',
            amount: '488.90',
            dateTime: DateTime.now().subtract(const Duration(days: 1)),
            category: categories[0])
    );
    expenses.add(
        ExpenseItem(
            name: 'Uber',
            amount: '350.00',
            dateTime: DateTime.now().subtract(const Duration(days: 2)),
            category: categories[1])
    );
  }

  void _onItemTapped(int? index) {
    if (index != null) {
      setState(() {
        _selectedIndex = index;
        _navigationController.value = index;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    _widgetTabs = <Widget>[
      Expenses(categories: categories, expenses: expenses),
      Categories(categories: categories),
    ];

    return Scaffold(
      backgroundColor: Colors.grey[100],
      body: Center(
        child: _widgetTabs.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: CircularBottomNavigation(
        tabItems,
        controller: _navigationController,
        selectedPos: _selectedIndex,
        barHeight: 60,
        selectedCallback: _onItemTapped,
        animationDuration: const Duration(milliseconds: 150),
      ),
    );
  }

  @override
  void dispose() {
    super.dispose();
    _navigationController.dispose();
  }
}