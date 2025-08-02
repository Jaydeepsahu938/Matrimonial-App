# Matrimonial-App
Matrimonial App is a modern matrimonial Android application designed to provide users with potential match profiles in a swipeable, card-style format inspired by platforms like Shaadi.com. It utilizes real-time data fetched from RandomUser.me API and combines modern Android development principles to ensure smooth, offline-first interaction.  
The app presents each profile with essential details (photo, name, age, location, etc.), and users can either Accept or Decline matches. These decisions are stored locally using Room database, allowing the app to function seamlessly even without an internet connection.

🔍 Core Features
Fetch profiles from API: https://randomuser.me/api
RecyclerView with swipeable match cards
Accept/Decline button with local state storage
Room Database for offline persistence
Syncs when back online
MVVM + Repository Pattern
Retrofit for API integration
Glide for image loading
ViewBinding for safer view access
Pagination support
Clean UI with rounded images, modern fonts & colors
Error handling and network failure UI


📱 Screenshots<br>
<img width="360" height="720" alt="1000000628" src="https://github.com/user-attachments/assets/5076c723-bcb6-44d1-8e31-1680ef6ac5a1" />
<img width="360" height="720" alt="1000000630" src="https://github.com/user-attachments/assets/70c9eef0-fb36-4c78-8fca-58cd67266cd5" /><br><br><br>
<img width="360" height="720" alt="1000000631" src="https://github.com/user-attachments/assets/c7584d46-3d0b-45f4-aff5-6db2e8be5340" />
<img width="360" height="720" alt="1000000629" src="https://github.com/user-attachments/assets/bd5f48c3-2991-4ea5-aedf-c11241eb1cfa" /><br>



🧱 Tech Stack
- Language: Kotlin
- Architecture: MVVM + Clean Architecture
- API Client: Retrofit
- Local Storage: Room Database
- Image Loader: Glide
- Async Operations: Kotlin Coroutines
- View Layer: RecyclerView + ViewBinding
