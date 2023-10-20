import 'package:flutter/material.dart';
import 'package:tarea5_flutter_app/models/category.dart';

class Categories extends StatefulWidget {
  final List<Category> categories;

  const Categories({super.key, required this.categories});

  @override
  State<Categories> createState() => _CategoriesState();
}

class _CategoriesState extends State<Categories> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[100],
      appBar: AppBar(
        centerTitle: true,
        title: const Text('Categorias'),
        actions: [
          IconButton(
            icon: const Icon(Icons.add_rounded),
            tooltip: 'Nueva Categoria',
            onPressed: () {
              _addCategory();
            },
          ),
        ],
      ),
      body: Container(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: <Widget>[
            Expanded(
              child: ListView.builder(
                itemCount: widget.categories.length,
                itemBuilder: (context, index) {
                  return Card(
                      child: ListTile(
                        leading: CircleAvatar(
                          backgroundColor: widget.categories[index].backgroundColor,
                          radius: 15,
                        ),
                        title: Text(
                          widget.categories[index].name,
                          style: TextStyle(
                            fontSize: 16,
                            fontWeight: FontWeight.bold,
                            color: Colors.grey[700],
                          ),
                        ),
                        trailing: IconButton(
                          icon: const Icon(Icons.close_rounded, color: Colors.red),
                          onPressed: () {
                            _deleteCategory(index);
                          },
                        ),
                      )
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }

  void _deleteCategory(int index) {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: const Text("Confirmar eliminación"),
            content: const Text("¿Estás seguro de que quieres eliminar esta categoría?"),
            actions: <Widget>[
              TextButton(
                child: const Text("Cancelar"),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
              TextButton(
                child: const Text("Eliminar"),
                onPressed: () {
                  setState(() {
                    widget.categories.removeAt(index);
                  });
                  Navigator.of(context).pop();
                },
              ),
            ],
          );
        }
    );
  }

  void _addCategory() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        String categoryText = "";

        return AlertDialog(
          title: const Text("Nueva Categoria"),
          content: TextField(
            onChanged: (value) {
              categoryText = value;
            },
          ),
          actions: <Widget>[
            TextButton(
              child: const Text("Agregar"),
              onPressed: () {
                if (categoryText.isNotEmpty) {
                  setState(() {
                    Category item = Category(name: categoryText);
                    widget.categories.add(item);
                  });
                }
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

}
