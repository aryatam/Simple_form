
# Simple Form Management System

This project implements a RESTful API for managing dynamic forms. The API allows users to create, update, delete, and publish forms. Each form contains various fields, and the API supports multiple field types such as text, number, boolean (checkbox), and date.

---

## Features

- **Create Forms**: Add new forms dynamically.
- **Update Forms**: Modify existing forms and their fields.
- **Delete Forms**: Remove forms from the database.
- **Field Management**: Add, update, or delete fields associated with forms.
- **Publishing**: Change the published status of forms.
- **Get Forms**: Retrieve form details using various endpoints.

---

## API Endpoints

| HTTP Method | Endpoint                  | Description                                     |
|-------------|---------------------------|-------------------------------------------------|
| `GET`       | `/forms/`                 | Retrieve the list of all forms.                |
| `POST`      | `/forms/`                 | Create a new form.                             |
| `GET`       | `/forms/{id}`             | Get details of a specific form by ID.          |
| `PUT`       | `/forms/{id}`             | Update an existing form by ID.                 |
| `DELETE`    | `/forms/{id}`             | Delete a form by ID.                           |
| `GET`       | `/forms/{id}/fields`      | Retrieve all fields of a specific form.        |
| `PUT`       | `/forms/{id}/fields`      | Update the fields of a specific form.          |
| `POST`      | `/forms/{id}/publish`     | Change the publish status of a form.           |

---

## Field Types

Supported field types include:
- `text`: Simple text input.
- `number`: Numeric input.
- `boolean`: Checkbox.
- `date`: Date input.

Each field contains:
- A unique `fieldName`.
- A `label` to display.
- A `type` defining its nature.
- A `defaultValue`.

---

## Requirements

- **Java JDK**: Version 11 or higher.
- **Spring Boot**: Framework for building the API.
- **Maven**: Dependency and build management.
- **H2 Database**: Embedded database for testing and persistence.

---

## How to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/simple-form-management.git
   cd simple-form-management
