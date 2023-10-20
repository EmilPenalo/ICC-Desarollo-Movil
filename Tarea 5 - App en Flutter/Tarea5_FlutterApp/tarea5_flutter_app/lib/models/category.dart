import 'dart:math';
import 'dart:ui';

class Category {
  final String name;
  final Color backgroundColor;

  Category({
    required this.name,
  }) : backgroundColor = _generateRandomPastelColor();

  static Color _generateRandomPastelColor() {
    final random = Random();
    final r = 200 + random.nextInt(56);
    final g = 200 + random.nextInt(56);
    final b = 200 + random.nextInt(56);
    return Color.fromARGB(255, r, g, b);
  }
}