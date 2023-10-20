import 'package:flutter/material.dart';
import 'package:fl_chart/fl_chart.dart';
import '../../models/expense_item.dart';
import '../../util/date_util.dart';

class ExpensesBarChart extends StatefulWidget {
  final List<ExpenseItem> expenses;

  const ExpensesBarChart({super.key, required this.expenses});

  @override
  State<ExpensesBarChart> createState() => _ExpensesBarChartState();
}

class _ExpensesBarChartState extends State<ExpensesBarChart> {
  @override
  Widget build(BuildContext context) {
    Map<String, double> expensesByDay = {};
    initializeData(expensesByDay);
    double maxY = (findMaxExpense(expensesByDay)/ 100).ceil() * 100;

    return BarChart(
      BarChartData(
        maxY: maxY,
        titlesData: FlTitlesData(
          rightTitles: const AxisTitles(sideTitles: SideTitles(showTitles: false)),
          leftTitles: const AxisTitles(sideTitles: SideTitles(showTitles: false)),
          topTitles: const AxisTitles(sideTitles: SideTitles(showTitles: false)),
          bottomTitles: AxisTitles(sideTitles: SideTitles(
            showTitles: true,
            getTitlesWidget: getBottomTitles,
          ))
        ),
        gridData: const FlGridData(
          show: false,
        ),
        borderData: FlBorderData(
          show: false,
        ),
        barGroups: _generateBarGroups(expensesByDay, maxY),
        barTouchData: BarTouchData(
          touchTooltipData: BarTouchTooltipData(
            tooltipBgColor: Colors.white,
            tooltipBorder: BorderSide(
              color: Colors.grey.shade800,
            ),
            direction: TooltipDirection.top,
            fitInsideHorizontally: true,
            fitInsideVertically: true,
          )
        )
      ),
    );
  }

  void initializeData(Map<String, double> expensesByDay) {
    final now = DateTime.now();
    final weekStart = now.subtract(Duration(days: now.weekday - 1));
    final weekEnd = weekStart.add(const Duration(days: 7));

    for (int i = 0; i < 7; i++) {
      final day = weekStart.add(Duration(days: i));
      expensesByDay[formatDateISO(day)] = 0.0;
    }

    for (var expense in widget.expenses) {
      if (isSameDay(expense.dateTime, weekStart) || isSameDay(expense.dateTime, weekEnd) || (expense.dateTime.isAfter(weekStart) && expense.dateTime.isBefore(weekEnd))) {
        final dayKey = formatDateISO(expense.dateTime);
        expensesByDay[dayKey] = expensesByDay[dayKey]! + double.parse(expense.amount);
      }
    }
  }

  List<BarChartGroupData> _generateBarGroups(Map<String, double> expensesByDay, double maxY) {
    List<BarChartGroupData> barGroups = [];

    int i = 0;
    for (var dayKey in expensesByDay.keys) {
      final barData = BarChartGroupData(
        x: i,
        barRods: [
          BarChartRodData(
            toY: expensesByDay[dayKey]!,
            color: Colors.grey[800],
            width: 25,
            borderRadius: BorderRadius.circular(4),
            backDrawRodData: BackgroundBarChartRodData(
              show: true,
              toY: maxY,
              color: Colors.white
            )
          ),
        ],
      );
      barGroups.add(barData);
      i++;
    }

    return barGroups;
  }

  double findMaxExpense(Map<String, double> expensesByDay) {
    double maxExpense = double.negativeInfinity;

    for (var expense in expensesByDay.values) {
      if (expense > maxExpense) {
        maxExpense = expense;
      }
    }

    return maxExpense;
  }

  Widget getBottomTitles(double value, TitleMeta meta) {
    const style = TextStyle(
      color: Colors.grey,
      fontWeight: FontWeight.w500,
      fontSize: 14,
    );

    Widget text;
    switch (value.toInt()) {
      case 0:
        text = const Text('L', style: style,);
        break;
      case 1:
        text = const Text('M', style: style,);
        break;
      case 2:
        text = const Text('M', style: style,);
        break;
      case 3:
        text = const Text('J', style: style,);
        break;
      case 4:
        text = const Text('V', style: style,);
        break;
      case 5:
        text = const Text('S', style: style,);
        break;
      case 6:
        text = const Text('D', style: style,);
        break;
      default:
        text = const Text('', style: style,);
        break;
    }
    
    return SideTitleWidget(axisSide: meta.axisSide, child: text);
  }
}


