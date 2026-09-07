# Demo Web Shop UI Tests

Навчальний UI-автоматизаційний фреймворк для сайту
[demowebshop.tricentis.com](https://demowebshop.tricentis.com/),
побудований на Java + Playwright + TestNG + Gradle.

## Stack

- **Java 17+**
- **Gradle** (with wrapper - `./gradlew`, глобальний Gradle не потрібен)
- **Playwright for Java 1.47** - керування браузером
- **TestNG 7.10** - структура тестів, DataProvider, listeners
- **Page Object Model** - організація UI-локаторів та дій

## Project Structure

```
LearningDemoShopUITest/
├── build.gradle                              - dependencies + tasks
├── src/main/
│   ├── java/com/qalight/demoshop/
│   │   ├── config/ConfigReader.java          - reads config.properties
│   │   ├── driver/DriverFactory.java         - Playwright lifecycle
│   │   └── pages/                            - Page Objects
│   │       ├── BasePage.java
│   │       ├── HomePage.java
│   │       ├── LoginPage.java
│   │       └── RegisterPage.java
│   └── resources/
│       ├── config.properties                 - defaults (in git)
│       └── config.local.properties           - secrets (git-ignored)
└── src/test/
    ├── java/com/qalight/demoshop/
    │   ├── driver/ScreenshotListener.java    - screenshot on failure
    │   ├── helpers/TestData.java             - unique test data generator
    │   └── tests/                            - test classes
    │       ├── BaseTest.java
    │       ├── RegistrationTest.java
    │       ├── LoginTest.java
    │       ├── LogoutTest.java
    │       └── SearchTest.java
    └── resources/
        └── testng.xml                        - suite configuration
```

## Setup (первинне налаштування)

### 1. Клонувати репо

```bash
git clone https://github.com/OleksandrLychak/LearningDemoShopUITest.git
cd LearningDemoShopUITest
```

### 2. Завантажити браузери Playwright

Playwright використовує власні збірки браузерів. Скачати всі три
(Chromium, Firefox, Webkit) - один раз для всієї машини:

```bash
./gradlew installPlaywrightBrowsers
```

Займає 3-5 хвилин при першому запуску (~290 MB завантаження).

### 3. Створити config.local.properties

Файл `config.local.properties` містить credentials тестового користувача.
Він у `.gitignore` і **не повинен** потрапити в репо.

Створи файл `src/main/resources/config.local.properties` з наступним вмістом:

```properties
test.user.email=your-registered-email@example.com
test.user.password=your-password
```
**Важливо:** без цього кроку `LoginTest` і `LogoutTest` (2 з 21 тестів у проєкті)
впадуть з `AssertionError`, бо намагатимуться залогінитись плейсхолдером
`CHANGE_ME_IN_LOCAL_PROPERTIES` замість реального акаунту. Це очікувана
поведінка - не bug, а сигнал "налаштуй credentials перед запуском".

Використай реальний акаунт, зареєстрований на demowebshop.tricentis.com.
Якщо його нема - зареєструйся вручну через `/register` перед запуском.

## How to Run

### Запустити всі тести

```bash
./gradlew test
```

Тести запускаються через `src/test/resources/testng.xml` як suite
"Demo Web Shop UI Suite". Тривалість повного прогону - 15-20 секунд
для 5 тестів.

### Запустити конкретний тест-клас

```bash
./gradlew test --tests "com.qalight.demoshop.tests.LoginTest"
```

### Дивитися HTML-звіт

Після запуску Gradle генерує звіт у `build/reports/tests/test/`:

```bash
open build/reports/tests/test/index.html
```

## Tests

Реалізовано 5 тестів за вимогами ДЗ:

| Test | Class | Description |
|------|-------|-------------|
| Successful registration | `RegistrationTest` | Новий користувач з унікальним email |
| Successful login | `LoginTest.userCanLoginWithValidCredentials` | Валідний користувач з config |
| Invalid login | `LoginTest.userCannotLoginWithInvalidCredentials` | Неправильні credentials, перевірка помилки |
| Logout | `LogoutTest` | Login → Logout → перевірка стану |
| Search product | `SearchTest` | Пошук з хедера, перевірка URL і title |

## Screenshots on Failure

`ScreenshotListener` (зареєстрований у `BaseTest` через `@Listeners`)
робить full-page скріншот при провалі будь-якого тесту та зберігає
в `build/screenshots/` з унікальним ім'ям
`ClassName_methodName_yyyyMMdd_HHmmss.png`.

Папка `build/` у `.gitignore`, скріншоти не потрапляють в репо.

## Configuration

Файл `config.properties` містить дефолтні налаштування:

```properties
base.url=https://demowebshop.tricentis.com
browser=chromium
headless=false
default.timeout=10000
screenshots.dir=build/screenshots
```

`config.local.properties` (git-ignored) перекриває значення - там
живуть credentials і локальні преференції.

Щоб запустити тести в headless-режимі, додай у `config.local.properties`:

```properties
headless=true
```

## Requirements Coverage

- [x] Gradle project (with wrapper)
- [x] Page Objects (BasePage / HomePage / LoginPage / RegisterPage)
- [x] Configuration file (config.properties + config.local.properties)
- [x] Base test (BaseTest with @BeforeMethod / @AfterMethod)
- [x] Screenshots on failure (ScreenshotListener via @Listeners)
- [x] 5 required tests