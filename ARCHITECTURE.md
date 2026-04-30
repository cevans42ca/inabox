# Architecture - inabox

## Overview
`inabox` is a personal inventory management system designed to help track items in containers (boxes). It prioritizes voice-driven interaction via iOS Siri Shortcuts while providing a web-based management interface.

The project serves a dual purpose:
1. **Practical:** Organizing household items.
2. **Educational:** Exploring Spring Boot, Angular, MongoDB, and multi-language support.

## System Components

### 1. Backend (Java / Spring Boot)
The backend takes on more responsibilities than in most applications.  Like most applications, the backend handles data persistence and search logic.  However, this backend does more response formatting than other applications.
The main frontend is driven by simple iOS Shortcuts that don't do any processing, requiring the backend to handle more heavy lifting.
- **Spring Boot 4 / Java 17**: Provides the REST API.
- **MongoDB**: Used for flexible schema storage of inventory items.
- **Content Helpers**: Specialized services (e.g., `EnglishHelper`) that transform raw data into natural language sentences. This simplifies client-side implementation, particularly for iOS Shortcuts, which can simply "speak" the text returned by the API.
- **Phonetic Search**: Implements normalization logic to handle voice-to-text inaccuracies common in Siri voice commands.

### 2. Primary Client (iOS Shortcuts)
The intended primary interface. It interacts with the `/api` endpoints to:
- **Query**: "Where is my [Item Name]?"
- **Mutation (Planned)**: "Add [Item] to [Box]", "Move [Box] to [Location]".

### 3. Management UI (Angular)
A web interface for bulk management and visualization. While not the primary daily-use interface, it provides a standard web-based view of the inventory.

## Data Model

### Inventory (MongoDB `inventory` collection)
Currently centered around the **Box** entity:
- `name`: Identifiable name of the container or item.
- `location`: A descriptive string of where the box is kept (e.g., "Top shelf of the garage closet").
- `description`: Additional details about the contents.
- `phonetic`: A normalized version of the name used for fuzzy voice matching.

**Future State**: The model will evolve to support **Items** nested within **Boxes**.

## Key Design Decisions

- **Logic in Backend**: Sentence construction and complex search logic are kept on the server. This keeps clients "dumb" and easy to implement across different platforms (Shortcuts, Web, or future CLI).
- **Phonetic Matching**: To compensate for Siri's transcription errors, the system uses phonetic normalization to ensure high-confidence matches even when the voice-to-text isn't perfect.
- **Flat Locations**: Locations are currently stored as strings that are descriptive enough for human retrieval, avoiding the complexity of nested location hierarchies for now.
- **Educational Multilingualism**: Although primarily used in English, the architecture includes `ContentHelperService` to demonstrate how to support multiple languages in a Spring Boot environment.

## Security
The application is designed for a single-user/household environment. It uses a simple admin-password-based security model for protecting the REST endpoints.
