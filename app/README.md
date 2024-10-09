GitHub Repository Downloader (Android App)

This Android application serves as a client for downloading GitHub repositories in ZIP format. It is built using Jetpack Compose for UI, Hilt for dependency injection, Room for local database storage, and Compose Navigation for seamless screen transitions. The app allows users to search for GitHub repositories, download them, and view a list of downloaded repositories.
Features:

    Repository Search: Search for any public GitHub repository by username.
    Download Repositories: Download repositories as ZIP files directly to the device's Downloads folder.
    Download Tracking: Track ongoing repository downloads in real-time and display progress in the UI.
    Repository History: View a list of previously downloaded repositories, stored locally in a Room database.
    User-Friendly UI: Built with Jetpack Compose to provide a modern, responsive interface.
    Hilt Dependency Injection: Manage dependencies efficiently using Hilt.
    MVVM Architecture: Follows a clean MVVM architecture for separation of concerns and easy maintainability.

Key Technologies:

    Jetpack Compose: For declarative UI design.
    Compose Navigation: For in-app navigation between screens.
    Room Database: To store the list of successfully downloaded repositories.
    DownloadManager API: To handle file downloads in the background.
    Hilt: For dependency injection.
    ViewModel: To manage UI-related data in a lifecycle-conscious way.
    Coroutines: For background processing and database operations.

How It Works:

    Search Repositories: The user can search for GitHub repositories by entering a username.
    Download Repositories: When the user selects a repository, the app triggers a background download using the DownloadManager API.
    Track Downloads: The app tracks the current downloading repository and displays it on the UI.
    List of Downloads: Successfully downloaded repositories are stored in a Room database and displayed in a list as "username - repository name".
    Download Notifications: The app shows notifications when a download is completed.