### Hexlet tests and linter status:
[![Actions Status](https://github.com/Rata0/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Rata0/java-project-78/actions)
[![Actions Status](https://github.com/Rata0/java-project-78/actions/workflows/ci-check.yml/badge.svg)](https://github.com/Rata0/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/5bd5beb312a35b9e884d/maintainability)](https://codeclimate.com/github/Rata0/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/5bd5beb312a35b9e884d/test_coverage)](https://codeclimate.com/github/Rata0/java-project-78/test_coverage)

# Валидатор данных
## Описание
"Валидатор данных" представляет собой библиотеку, с помощью которой можно проверять корректность любых данных. Подобные библиотеки широко распространены в различных языках программирования, поскольку большинство программ работают с внешними данными, которые необходимо проверять на корректность. В первую очередь речь идет о данных форм, заполняемых пользователями.
## Пример использования

```
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// Строки
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false

// Числа
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// Объект Map с поддержкой проверки структуры
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human2); // false
```

В примере демонстрируется использование "Валидатора данных" для проверки строк, чисел и объектов типа Map. Для каждого типа данных создается соответствующая схема валидации, к которой можно применять различные правила проверки. Например, для строк можно задать правило обязательного наличия (required()), для чисел - правило положительного значения (positive()), а для объектов типа Map - правило проверки структуры (shape()), где для каждого ключа задается своя схема валидации.

### Валидация строк

Для валидации строк используется класс StringSchema. Объект этого класса создается путем вызова метода string() на экземпляре класса Validator:

```
import hexlet.code.Validator;

Validator v = new Validator();
StringSchema schema = v.string();
```

После создания объекта StringSchema, он предоставляет следующие методы для настройки правил проверки:

#### required()
Делает поле обязательным для заполнения. После применения этого метода строка не может быть пустой (null) или содержать только пробельные символы.

Пример использования:

```
StringSchema schema = v.string().required();
schema.isValid(""); // false
schema.isValid("   "); // false
schema.isValid("hello"); // true
```

#### minLength(int length)
Задает минимальную длину строки. Строка должна быть равна или длиннее указанной длины.

Пример использования:

```
StringSchema schema = v.string().minLength(5);
schema.isValid("hi"); // false
schema.isValid("hello"); // true
```
#### contains(String substring)
Добавляет ограничение, что строка должна содержать указанную подстроку.

Пример использования:

```
StringSchema schema = v.string().contains("world");
schema.isValid("hello"); // false
schema.isValid("hello world"); // true
```

### Валидация чисел

Для валидации чисел используется класс NumberSchema. Объект этого класса создается путем вызова метода number() на экземпляре класса Validator:

```
import hexlet.code.Validator;

Validator v = new Validator();
NumberSchema schema = v.number();
```

После создания объекта NumberSchema, он предоставляет следующие методы для настройки правил проверки:

#### required()
Делает поле обязательным для заполнения. После применения этого метода число не может быть null.

Пример использования:

```
NumberSchema schema = v.number().required();
schema.isValid(null); // false
schema.isValid(42); // true
```

#### positive()
Добавляет ограничение, что число должно быть положительным.

Пример использования:

```
NumberSchema schema = v.number().positive();
schema.isValid(-1); // false
schema.isValid(0); // false
schema.isValid(5); // true
```

#### range(int start, int end)
Задает допустимый диапазон значений числа, включая границы диапазона.

Пример использования:

```
NumberSchema schema = v.number().range(5, 10);
schema.isValid(3); // false
schema.isValid(5); // true
schema.isValid(7); // true
schema.isValid(10); // true
schema.isValid(11); // false
```

### Валидация объектов типа Map

Для валидации объектов типа Map используется класс MapSchema. Объект этого класса создается путем вызова метода map() на экземпляре класса Validator:

```
import hexlet.code.Validator;
import java.util.Map;

Validator v = new Validator();
MapSchema schema = v.map();
```

После создания объекта MapSchema, он предоставляет следующие методы для настройки правил проверки:

#### required()
Делает поле обязательным для заполнения. После применения этого метода Map не может быть null.

Пример использования:

```
MapSchema schema = v.map().required();
schema.isValid(null); // false
schema.isValid(new HashMap<>()); // true
```
#### sizeof(int size)
Добавляет ограничение на размер Map. Количество пар ключ-значение в объекте Map должно быть равно заданному размеру.

Пример использования:

```
MapSchema schema = v.map().sizeof(2);
schema.isValid(new HashMap<>()); // false
Map<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.put("b", 2);
schema.isValid(map); // true
map.put("c", 3);
schema.isValid(map); // false
```

#### Вложенная валидация

При работе со сложными данными бывает нужно проверять не только сам объект Map, но и данные внутри него. Метод shape(Map<String, BaseSchema<?>> schemas) используется для определения свойств объекта Map и создания схемы для валидации их значений. Каждому свойству объекта Map привязывается свой набор ограничений (своя схема), что позволяет более точно контролировать данные:

```
// Создаем набор схем для проверки значений каждого ключа
Map<String, BaseSchema<String>> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "firstName" и "lastName"
schemas.put("firstName", v.string().required()); // Имя должно быть обязательной непустой строкой
schemas.put("lastName", v.string().required().minLength(2)); // Фамилия должна быть обязательной строкой длиной не менее 2 символов

// Настраиваем схему `MapSchema` с вложенными схемами
MapSchema schema = v.map().shape(schemas);

// Проверяем объекты
Map<String, String> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema.isValid(human1); // true

Map<String, String> human2 = new HashMap<>();
human2.put("firstName", "John");
human2.put("lastName", null); // Нарушено ограничение required для lastName
schema.isValid(human2); // false

Map<String, String> human3 = new HashMap<>();
human3.put("firstName", "Anna");
human3.put("lastName", "B"); // Нарушено ограничение minLength для lastName
schema.isValid(human3); // false
```

В этом примере мы создаем MapSchema для валидации объектов Map, которые представляют человека. Для каждого ключа ("firstName" и "lastName") создается отдельная схема валидации с помощью v.string() и необходимыми ограничениями. Затем эти схемы объединяются в Map и передаются в метод shape().

Метод shape() позволяет задать схему проверки для каждого ключа Map отдельно, что дает возможность более точно контролировать вложенные данные.