### Documentation of RestController Classes

The following `RestController` classes were found in the backend of the project.

#### 1. `ApiController`
- **Path:** `ca.quines.inabox.ApiController`
- **Base Request Mapping:** `/api`
- **Description:** This is the primary API controller that handles data retrieval for "boxes". It integrates with `BoxService` for database operations and `PhoneticSearchUtil` for searching.
- **Endpoints:**
    - `GET /api/overview`: Returns a list of the last 10 boxes inserted into the database.
    - `POST /api/getBoxByName`: Retrieves a specific box by its name, provided in the request body.
    - `POST /api/getBoxByPhonetic` (produces `text/plain`): Performs a phonetic search for boxes and returns a string representation matched to a sentence using `ContentHelperService`.
    - `POST /api/getBoxByPhonetic` (produces `application/json`): Performs a phonetic search and returns a list of matching `Box` objects in JSON format.

#### 2. `RootController`
- **Path:** `ca.quines.inabox.RootController`
- **Description:** Handles requests to the application's root URL.
- **Endpoints:**
    - `GET /`: Returns a placeholder string "The root page goes here.".