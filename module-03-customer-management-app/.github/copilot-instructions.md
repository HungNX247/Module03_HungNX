# Customer Management App - AI Coding Agent Instructions

## Project Overview
Jakarta EE (Servlet 6.0) web application for customer management with a layered architecture: Controller → Service → DAO → Database. Uses Gradle for builds, MySQL 8+ for persistence, and JSP for views.

## Architecture & Components

### Layered Structure (Critical Data Flow)
1. **Controllers** (`controller/`): `@WebServlet` beans handle HTTP requests, initialize services in `init()`, map URL patterns to actions via switch statements
   - `CustomerController`: Manages CRUD operations for customers (routes: `/customers`, `/customer/detail`, `/customer/add`, `/customer/edit`, `/customer/remove`)
   - `HomeController`: Landing page route (`/home`)
2. **Service Layer** (`model/service/`): `CustomerService` orchestrates DAO calls and DTO transformations. No singleton pattern—services instantiated per request
3. **DAO Layer** (`model/dao/`): `CustomerDao` handles all SQL queries using raw JDBC (PreparedStatement). Returns entity objects to service
4. **Entity & DTO**: `Customer` (entity) has ID auto-generation logic; `CustomerDto` is request/response object
5. **Mapper** (`mapper/`): `CustomerMapper` provides static utility methods: `entityToDto()`, `dtoToEntity()`, `entitiesToDtoList()`
6. **View Layer** (`webapp/WEB-INF/view/`): JSP pages use JSTL tags (`<c:url>`, `<c:forEach>`); CSS in `/static/css/`

### Database
- Schema: `customer_db`
- Table: `customers` (id, name, position, office, age, start_date, salary)
- Seed data: 3 sample employees
- Connection: `JdbcConnection.getConnection()` returns MySQL connections via hardcoded credentials (localhost:3306)

### Request Flow Example
`/customers` GET → `CustomerController.doGet()` → `customerService.findAll()` → `customerDao.getAll()` → maps results via `CustomerMapper.entitiesToDtoList()` → forwards to `index.jsp` with `customers` attribute

## Build & Development Workflow

### Gradle Commands
- **Build WAR**: `./gradlew build` → output: `build/libs/*.war`
- **Run tests**: `./gradlew test` (uses JUnit 5)
- **Clean**: `./gradlew clean`
- **Java version**: 21 (set in `build.gradle` sourceCompatibility)

### Key Dependencies
- `jakarta.servlet:jakarta.servlet-api:6.1.0` — Web container API
- `jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.2` — JSP Standard Tag Library
- `com.mysql:mysql-connector-j:9.5.0` — MySQL driver
- `opensymphony:sitemesh:2.7.0-M1` — Page layout/templating
- `at.favre.lib:bcrypt:0.10.2` — Password hashing (imported but not yet used)

### IDE Setup
- Deploy WAR to Tomcat 10+ (Jakarta EE compatible)
- Database: Create `customer_db` schema and run [customer_db.sql](src/main/resources/db/customer_db.sql)
- Config: Update DB credentials in [JdbcConnection.java](src/main/java/com/codegym/connection/JdbcConnection.java) if needed

## Code Patterns & Conventions

### Controller Pattern
```java
@WebServlet(name = "...", urlPatterns = { "/path1", "/path2" })
public class YourController extends HttpServlet {
    private YourService service;
    
    @Override
    public void init() throws ServletException {
        service = new YourService();  // Instance per controller
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch(action) {
            case "/path1":
                // fetch from service, set request attributes, forward
                req.setAttribute("data", service.getData());
                req.getRequestDispatcher("/WEB-INF/view/...jsp").forward(req, resp);
                break;
        }
    }
}
```

### DAO Query Pattern
- Use `JdbcConnection.getConnection()` to get connections
- Always use `PreparedStatement` with parameterized queries (SQL injection prevention)
- Map `ResultSet` to entities manually; no ORM used
- Close connections after use (note: current code has potential resource leak; consider try-with-resources)

### Service-to-DTO Transformation
Services never return entities to controllers; always map via `CustomerMapper`:
```java
public CustomerDto find(int id) {
    Customer entity = customerDao.get(id);
    return CustomerMapper.entityToDto(entity);  // Always transform
}
```

### JSP View Structure
- Use `<%@ taglib ... %>` declarations at top
- Data passed via `request.getAttribute()` from controller
- JSTL `<c:url>` for URL generation (respects context path)
- External CSS/JS in `/static/` directory
- Layout managed by SiteMesh (configured in deployment descriptor)

## Testing & Debugging
- JUnit 5 framework (`org.junit.jupiter:junit-jupiter-api`)
- No integration tests currently; focus on DAO/Service unit tests
- Debugging: Use IDE debugger on local Tomcat deployment (breakpoints in controllers, services, DAO)

## Important Notes & Gotchas
1. **No singletons**: Each controller instance creates a new service. Services create new DAOs on instantiation
2. **Connection management**: JDBC connections not closed in all code paths; refactor with try-with-resources for production
3. **Date handling**: `ResultSet.getDate()` returns `java.sql.Date`; ensure JSP formatting handles this
4. **URL routing**: All routes must match `@WebServlet` urlPatterns exactly; no wildcard support for actions (uses `getServletPath()` switch)
5. **Security**: Hardcoded DB credentials in source code; move to environment variables or properties file
6. **Package structure**: `com.codegym.*` is default; maintain this for consistency

## Common Tasks

### Add a New Customer Field
1. Add column to `customers` table in [customer_db.sql](src/main/resources/db/customer_db.sql)
2. Update [Customer.java](src/main/java/com/codegym/model/entity/Customer.java) entity class (fields, constructors, getters/setters)
3. Update [CustomerDto.java](src/main/java/com/codegym/model/dto/CustomerDto.java) with matching field
4. Update [CustomerMapper.java](src/main/java/com/codegym/mapper/CustomerMapper.java) in both `entityToDto()` and `dtoToEntity()`
5. Update [CustomerDao.java](src/main/java/com/codegym/model/dao/CustomerDao.java): add field assignment in all ResultSet mappings (getAll, get, fetch, insert, update)
6. Update JSP views in `/webapp/WEB-INF/view/customer/` (index.jsp, detail.jsp, add.jsp, edit.jsp)

### Fix a Bug in Customer CRUD
- Trace the request through controller switch case → service method → DAO query
- Check JSP form parameter names match controller `req.getParameter()` calls
- Verify SQL in DAO uses parameterized queries with correct bind order

### Add Validation
- Implement in `CustomerService` methods before DAO calls
- Throw exceptions or return error DTOs; controllers must handle and forward to error view
- No validation framework currently in use; use manual bean validation
