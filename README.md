# 🚀 Blog Service (Spring Boot + REST API)

This is a **Blog Management Service** built using **Spring Boot**. It provides:  
✅ CRUD operations for **Blogs**  
✅ **Pagination & Sorting**  
✅ RESTful API endpoints for managing blogs

---

## ⚡ Tech Stack
- **Spring Boot** (REST API)
- **Spring Data JPA** (Database Interaction)
- **MySQL** (Data Storage)
- **Spring Cache** (Caching)
- **Lombok** (Code Simplification)

---

## 🚀 Installation & Setup

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/mohitkmeena/blog-service.git
cd blog-service
```
### 2️⃣ Add/Update `application.properties`
```sh
server.port=8080

# Database Config
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
## 🔑 Api Endpoints
### 1️⃣   Get Endpoints
📌 Get Single Blog with blog id
```shell
 GET /v1/blogs/blog/{id}
```
🔹 Respone (JSON)
```shell
{
  "id": "234567fyeu8493jw",
  "title": "My First Blog",
  "content": "This is a sample blog content for testing.",
  "author": "Mohit Kumar Meena",
  "createdAt": "2024-03-13T10:30:00Z"
}
```
📌 Get All Blogs with pagination
```shell
 GET /v1/blogs/blog?pageNum={pageNum}&pageSize={pageSize}
```
🔹 Respone (JSON)
```shell
{
  "blogDtos": [
    {
      "id": "101",
      "title": "Spring Boot Basics",
      "content": "Introduction to Spring Boot...",
      "author": "Mohit Kumar Meena",
      "createdAt": "2024-03-13T10:30:00Z"
    },
    {
      "id": "102",
      "title": "Understanding REST APIs",
      "content": "This blog explains REST API concepts...",
      "author": "John Doe",
      "createdAt": "2024-03-12T09:15:00Z"
    }
  ],
  "pageNum": 0,
  "pageSize": 5,
  "totalElem": 50,
  "totalPage": 10,
  "isLast": false
}
```
### 2️⃣   Post Endpoints
📌 Add a blog

```shell
POST /v1/blogs/blog
```
🔹 Request Body  (JSON)
```shell
{
      
      "title": "Spring Boot Basics",
      "content": "Introduction to Spring Boot...",
      "author": "Mohit Kumar Meena",
      "createdAt": "2024-03-13"
    }
```
🔹 Response (JSON)
```shell
{
     "id": "102",
      "title": "Spring Boot Basics",
      "content": "Introduction to Spring Boot...",
      "author": "Mohit Kumar Meena",
      "createdAt": "2024-03-13"
    }
```
### 3️⃣   PUT Endpoints
📌 Update a blog

```shell
PUT /v1/blogs/blog
```
🔹 Request Body  (JSON)
```shell
{
      "id": "102",
      "title": "Spring Boot Basics",
      "content": "Introduction to Spring Boot...",
      "author": "Mohit Kumar Meena",
      "createdAt": "2024-03-13"
    }
```
🔹 Response (JSON)
```shell
{
     "id": "102",
      "title": "Spring Boot Basics",
      "content": "Introduction to Spring Boot...",
      "author": "Mohit Meena",
      "createdAt": "2024-03-13"
    }
```
## 4️⃣   DELETE Endpoints
📌 DELETE a blog with id

```shell
DELETE /v1/blogs/blog/{id}
```
🔹 Response (JSON)
```shell
"blog deleted successfully"
```
