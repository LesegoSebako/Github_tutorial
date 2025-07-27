Android Studio Jetpack Compose - Common Errors & Quick Fixes
1. Preview Not Working

Error:

    Preview pane shows "Cannot preview this file" or is blank.

Solution:

    Make sure your composable has no required parameters in the preview.

    Add @Preview annotation and wrap it in your app's theme.

kotlin

@Preview(showBackground = true)  
@Composable  
fun PreviewExample() {  
    YourAppTheme {  
        Greeting(name = "Android")  
    }  
}  

If still not working:

    Build → Rebuild Project

    File → Invalidate Caches / Restart

2. "Unresolved Reference" (Missing Imports)

Error:

    Red underline on Text, Button, Column, etc.

Solution:

    Press Alt + Enter (Windows) / Option + Return (Mac) to auto-import.

    If it doesn’t work, manually add:

kotlin

import androidx.compose.material3.Text  
import androidx.compose.material3.Button  
import androidx.compose.foundation.layout.Column  

3. State Not Updating (UI Doesn’t Change)

Error:

    Clicking a button doesn’t update text/counter.

Solution:

    Use remember { mutableStateOf() } for variables that should trigger UI updates.

✅ Correct:
kotlin

var count by remember { mutableStateOf(0) }  

Button(onClick = { count++ }) {  
    Text("Count: $count")  
}  

❌ Wrong (won’t update UI):
kotlin

var count = 0 // ← Not observable!  

Button(onClick = { count++ }) {  
    Text("Count: $count")  
}  

4. "Type Mismatch" (Wrong Composable Usage)

Error:

    Passing a String where a @Composable function is expected.

Solution:

    Wrap text in Text() composable.

✅ Correct:
kotlin

Column {  
    Text("Hello") // ← Correct  
}  

❌ Wrong:
kotlin

Column {  
    "Hello" // ← Error: Not a composable  
}  

5. Modifier Conflicts (Overlapping Properties)

Error:

    Crashes with "Modifier.fillMaxSize() and Modifier.width() conflict".

Solution:

    Don’t mix conflicting modifiers (e.g., fillMaxWidth() + width()).

✅ Correct:
kotlin

Box(modifier = Modifier.fillMaxWidth().height(100.dp))  

❌ Wrong:
kotlin

Box(modifier = Modifier.fillMaxWidth().width(200.dp)) // ← Crash!  

6. App Crashes on Launch (NullPointerException)

Error:

    App crashes immediately with NullPointerException.

Solution:

    Check AndroidManifest.xml for missing MainActivity declaration:

xml

<activity  
    android:name=".MainActivity"  
    android:exported="true">  
    <intent-filter>  
        <action android:name="android.intent.action.MAIN" />  
        <category android:name="android.intent.category.LAUNCHER" />  
    </intent-filter>  
</activity>  

7. "Composable Invocation Failed" (Incorrect Scope)

Error:

    Calling a composable function from a non-composable scope.

Solution:

    Only call composables (Text(), Button(), etc.) inside other @Composable functions.

✅ Correct:
kotlin

@Composable  
fun MyScreen() {  
    Text("Hello") // ← Inside a composable  
}  

❌ Wrong:
kotlin

fun someNormalFunction() {  
    Text("Hello") // ← Error: Not in a @Composable  
}  

8. Navigation Crashes (Wrong Route Name)

Error:

    App crashes when navigating with NavController.

Solution:

    Ensure the route name matches exactly (case-sensitive!).

✅ Correct:
kotlin

NavHost(navController, startDestination = "home") {  
    composable("home") { HomeScreen() }  
    composable("details") { DetailsScreen() }  
}  

// Navigate like this:  
navController.navigate("details")  

❌ Wrong:
kotlin

navController.navigate("DETAILS") // ← Crash! (Case mismatch)  

9. Missing Dependencies (Gradle Errors)

Error:

    Unresolved reference: material3 or NavController not found.

Solution:

    Add these to app/build.gradle.kts:

kotlin

dependencies {  
    implementation("androidx.compose.material3:material3")  
    implementation("androidx.navigation:navigation-compose:2.7.7")  
}  

    Sync Project after changes.

10. Preview Fails with "Unsupported @Composable"

Error:

    Preview crashes with composable function issues.

Solution:

    Ensure preview composables don’t use ViewModel or real data.

✅ Correct for Preview:
kotlin

@Preview  
@Composable  
fun PreviewGreeting() {  
    Greeting(name = "Preview Name")  
}  

❌ Wrong:
kotlin

@Preview  
@Composable  
fun PreviewGreeting() {  
    Greeting(viewModel.name) // ← Avoid ViewModel in preview  
}  

Quick Debugging Tips

    Check Logcat (View → Tool Windows → Logcat) for crash details.

    Comment out code piece by piece to find the problem.

    Rebuild Project (Build → Rebuild).

    Restart Android Studio if things get stuck.

🚀 Good luck on your exam! If stuck, check for typos first—most errors are small mistakes!


1. "Recomposition Loop" (Infinite Updates)

Error:

    UI keeps refreshing infinitely.
    Fix:

    Avoid modifying state inside LaunchedEffect or composables without conditions.

kotlin

var count by remember { mutableStateOf(0) }  
LaunchedEffect(Unit) {  
    count++ // ← Causes infinite loop!  
}  

✅ Fix: Use a condition (e.g., if (count < 10) count++).
2. "NullPointerException" in rememberSaveable

Error:

    App crashes when restoring state.
    Fix:

    Use rememberSaveable with a Saver for complex objects.

kotlin

val data = rememberSaveable { mutableStateOf("") }  

3. "IllegalStateException: Already disposed"

Error:

    Happens when accessing a composable after it’s destroyed.
    Fix:

    Use LaunchedEffect for async operations.

kotlin

LaunchedEffect(key1 = Unit) {  
    fetchData() // Safe coroutine scope  
}  

4. "Modifier.weight() Not Working in Row/Column"

Error:

    Weight doesn’t distribute space properly.
    Fix:

    Ensure parent has a defined size (e.g., fillMaxWidth).

kotlin

Row(Modifier.fillMaxWidth()) {  
    Text("Left", Modifier.weight(1f))  
    Text("Right", Modifier.weight(1f))  
}  

5. "Preview Parameter Error"

Error:

    Preview fails when composable requires parameters.
    Fix:

    Provide default values in preview.

kotlin

@Preview  
@Composable  
fun PreviewWithParams() {  
    MyComposable(text = "Preview Text")  
}  

6. "MutableState Not Updating in List"

Error:

    List items don’t update when state changes.
    Fix:

    Use key = { item.id } in LazyColumn.

kotlin

LazyColumn {  
    items(items, key = { it.id }) { item ->  
        Text(item.name)  
    }  
}  

7. "UnsupportedOperationException: Empty List"

Error:

    Trying to modify an immutable List.
    Fix:

    Use mutableStateListOf.

kotlin

val list = remember { mutableStateListOf<String>() }  
list.add("Item") // ← Works  

8. "Composable Calls in Wrong Order"

Error:

    UI renders incorrectly due to conditional logic.
    Fix:

    Avoid if/else branches with different composables.

❌ Wrong:
kotlin

if (condition) Text("A")  
Button(...)  
else Text("B") // ← Inconsistent structure  

✅ Fix: Restructure logic to keep composables stable.
9. "ViewModel Not Surviving Configuration Changes"

Error:

    ViewModel resets on screen rotation.
    Fix:

    Use viewModel() (from lifecycle-viewmodel-compose).

kotlin

val vm: MyViewModel = viewModel()  

10. "Back Handler Not Working"

Error:

    Back button doesn’t trigger custom logic.
    Fix:

    Use BackHandler.

kotlin

BackHandler {  
    // Handle back press  
}  

11. "Image Not Loading from URL"

Error:

    Image fails to load network images.
    Fix:

    Use Coil or Glide library.

kotlin

implementation("io.coil-kt:coil-compose:2.6.0")  

kotlin

AsyncImage(  
    model = "https://example.com/image.jpg",  
    contentDescription = "Network Image"  
)  

12. "Keyboard Pushing UI Up (IME Overlap)"

Error:

    Keyboard covers TextField.
    Fix:

    Use imePadding().

kotlin

Column(Modifier.imePadding()) {  
    TextField(...)  
}  

13. "Animation Not Smooth"

Error:

    Animation stutters.
    Fix:

    Use animate*AsState.

kotlin

val alpha by animateFloatAsState(if (visible) 1f else 0f)  
Box(Modifier.alpha(alpha))  

14. "Navigation Duplicate Backstack"

Error:

    Multiple copies of the same screen in backstack.
    Fix:

    Use popUpTo or launchSingleTop.

kotlin

navController.navigate("screen") {  
    popUpTo("home")  
}  

15. "Coroutine Not Cancelled on Disposal"

Error:

    Memory leak from running coroutines.
    Fix:

    Use rememberCoroutineScope.

kotlin

val scope = rememberCoroutineScope()  
scope.launch { fetchData() }  

16. "Scaffold TopAppBar Not Updating"

Error:

    TopAppBar title doesn’t change.
    Fix:

    Re-compose with state.

kotlin

val title by remember { mutableStateOf("Default") }  
Scaffold(  
    topBar = { TopAppBar(title = { Text(title) }) }  
)  

17. "LazyColumn Scroll Position Reset"

Error:

    List scrolls to top on recomposition.
    Fix:

    Use rememberLazyListState.

kotlin

val state = rememberLazyListState()  
LazyColumn(state = state) { ... }  

18. "TextField Cursor Jumping"

Error:

    Cursor moves unexpectedly.
    Fix:

    Avoid recreating TextField keys.

kotlin

TextField(  
    value = text,  
    onValueChange = { text = it },  
    key = "myTextField" // ← Stable key  
)  

19. "Composable Not Fitting Screen"

Error:

    Content clipped or overflowing.
    Fix:

    Use verticalScroll or horizontalScroll.

kotlin

Column(Modifier.verticalScroll(rememberScrollState())) {  
    // Long content  
}  

20. "Theme Colors Not Applying"

Error:

    UI ignores MaterialTheme colors.
    Fix:

    Wrap in MaterialTheme.

kotlin

MaterialTheme {  
    Button(...) // ← Uses theme colors  
}  

21. "Side-Effect Not Triggering"

Error:

    LaunchedEffect doesn’t run.
    Fix:

    Add a key parameter.

kotlin

LaunchedEffect(key1 = userId) {  
    loadUser(userId)  
}  

22. "State Hoisting Confusion"

Error:

    Parent and child composables fight over state.
    Fix:

    Lift state up.

kotlin

@Composable  
fun Parent() {  
    var text by remember { mutableStateOf("") }  
    Child(text, onTextChange = { text = it })  
}  

23. "Preview Too Slow"

Error:

    Preview takes forever to load.
    Fix:

    Simplify preview or use @PreviewLightDark.

kotlin

@PreviewLightDark  
@Composable  
fun FastPreview() { ... }  

24. "Missing @Composable Annotation"

Error:

    Function doesn’t work in UI.
    Fix:

    Add @Composable.

kotlin

@Composable // ← Don’t forget this!  
fun MyButton() { ... }  

25. "ViewModel Initialization Crash"

Error:

    ViewModel constructor fails.
    Fix:

    Use ViewModelProvider.Factory.

kotlin

val vm: MyViewModel = viewModel(  
    factory = MyViewModelFactory()  
)  

26. "Navigation Argument Type Mismatch"

Error:

    Passing wrong argument type.
    Fix:

    Define argument types in NavHost.

kotlin

composable(  
    "details/{id}",  
    arguments = listOf(navArgument("id") { type = NavType.IntType }  
)  

27. "Composable In Wrong Scope"

Error:

    BoxScope or ColumnScope misuse.
    Fix:

    Use scope-specific modifiers.

kotlin

Box {  
    Text(Modifier.align(Alignment.Center)) // ← BoxScope  
}  

28. "Text Overflow Ellipsis Not Working"

Error:

    Text doesn’t truncate with ....
    Fix:

    Use overflow = TextOverflow.Ellipsis.

kotlin

Text(  
    "Long text...",  
    maxLines = 1,  
    overflow = TextOverflow.Ellipsis  
)  

29. "Drawer Not Closing on Navigation"

Error:

    ModalDrawer stays open.
    Fix:

    Close manually with drawerState.

kotlin

val drawerState = rememberDrawerState(DrawerValue.Closed)  
ModalDrawer(  
    drawerState = drawerState,  
    onClose = { drawerState.close() }  
)  

30. "Performance Lag in Lists"

Error:

    LazyColumn stutters.
    Fix:

    Use key and contentType.

kotlin

LazyColumn {  
    items(  
        items,  
        key = { it.id },  
        contentType = { it.type }  
    ) { item ->  
        ItemView(item)  
    }  
}  

Final Tips

    Read Logcat: Most errors are logged there.

    Simplify: Break down complex UIs into smaller composables.

    Rebuild Often: Catch errors early.

    31. "Ambiguous Modifier Order" Error

Error: UI renders incorrectly due to modifier sequence
Fix: Remember modifiers apply bottom-to-top:
kotlin

// Wrong: Padding applies to bordered area
Modifier.border(2.dp, Color.Black).padding(8.dp)

// Right: Padding applies inside border
Modifier.padding(8.dp).border(2.dp, Color.Black)

32. "Unintended Recompositions"

Error: Too many recompositions slowing app
Fix: Use derivedStateOf for derived values:
kotlin

val scrollState = rememberLazyListState()
val showButton by remember {
    derivedStateOf { scrollState.firstVisibleItemIndex > 0 }
}

33. "Missing contentDescription"

Error: Accessibility warnings for Images/IconButtons
Fix: Always add descriptions:
kotlin

Image(
    painter = painterResource(R.drawable.logo),
    contentDescription = "App logo" // Required
)

34. "ViewModel Initialization in Preview"

Error: Preview crashes with ViewModel usage
Fix: Mock data for previews:
kotlin

@Preview
@Composable
fun PreviewScreen() {
    MyScreen(uiState = mockUiState) // Don't use real VM
}

35. "Incorrect Scaffold Usage"

Error: Missing scaffold slots or wrong order
Fix: Follow proper scaffold structure:
kotlin

Scaffold(
    topBar = { TopAppBar(...) },
    floatingActionButton = { Fab(...) },
    content = { padding -> Content(padding) }
)

36. "Text Style Not Applying"

Error: Custom text styles ignored
Fix: Use MaterialTheme.typography:
kotlin

Text(
    text = "Hello",
    style = MaterialTheme.typography.headlineMedium
)

37. "List Index Out of Bounds"

Error: Crash when accessing list items
Fix: Check bounds first:
kotlin

LazyColumn {
    itemsIndexed(items) { index, item ->
        if (index < items.size) ItemView(item)
    }
}

38. "Animation Jumping"

Error: Animation resets unexpectedly
Fix: Use transitionSpec:
kotlin

animateContentSize(
    animationSpec = spring(
        dampingRatio = 0.6f,
        stiffness = Spring.StiffnessLow
    )
)

39. "Coroutine Scope Cancellation"

Error: Coroutines leak memory
Fix: Use DisposableEffect:
kotlin

DisposableEffect(Unit) {
    val job = CoroutineScope(Dispatchers.IO).launch { ... }
    onDispose { job.cancel() }
}

40. "Theme Not Propagating"

Error: Child composables ignore theme
Fix: Explicitly pass colors:
kotlin

CompositionLocalProvider(
    LocalContentColor provides Color.Red
) {
    ChildComposable() // Will use red text
}

41. "Navigation Duplicate Routes"

Error: Multiple routes with same path
Fix: Use unique route names:
kotlin

NavHost(navController, "home") {
    composable("home") { ... }
    composable("profile/{userId}") { ... } // Unique
}

42. "State Hoisting Confusion"

Error: State management becomes messy
Fix: Follow unidirectional flow:
kotlin

@Composable
fun MyScreen(state: State, onEvent: (Event) -> Unit) {
    Button(onClick = { onEvent(Event.Click) })
}

43. "Modifier Clipping Issues"

Error: Content clipped incorrectly
Fix: Use clipToBounds:
kotlin

Box(Modifier.clipToBounds()) {
    Image(...) // Won't overflow
}

44. "Text Baseline Alignment"

Error: Text misaligned in Rows
Fix: Use alignByBaseline:
kotlin

Row {
    Text("Hi", Modifier.alignByBaseline())
    Text("There", Modifier.alignByBaseline())
}

45. "Configuration Change Crash"

Error: State lost on rotation
Fix: Use rememberSaveable:
kotlin

var state by rememberSaveable { mutableStateOf("") }

46. "Overlapping Click Areas"

Error: Multiple click handlers conflict
Fix: Use combinedClickable:
kotlin

Box(Modifier.combinedClickable(
    onClick = { ... },
    onLongClick = { ... }
))

47. "Font Loading Failure"

Error: Custom fonts not showing
Fix: Register fonts properly:
kotlin

val fontFamily = FontFamily(
    Font(R.font.my_font, FontWeight.Normal)
)
Text("Hello", fontFamily = fontFamily)

48. "Z-Index Ordering"

Error: Items stack incorrectly
Fix: Use explicit zIndex:
kotlin

Box(Modifier.zIndex(1f)) { TopItem() }
Box(Modifier.zIndex(0f)) { BottomItem() }

49. "Keyboard Avoiding View"

Error: Keyboard covers inputs
Fix: Use WindowInsets:
kotlin

Modifier.imePadding()
    .navigationBarsPadding()
    .systemBarsPadding()

50. "Composition Local Missing"

Error: LocalContext not available
Fix: Provide required locals:
kotlin

CompositionLocalProvider(
    LocalContext provides context
) {
    MyComposable() // Can access LocalContext.current
}

Bonus Pro Tips:

    Use @Stable for classes passed to composables to prevent unnecessary recompositions

    Profile with Layout Inspector to debug UI issues

    Check recomposition counts with debugInspectorInfo

    Avoid heavy computations in composables - move to ViewModel

    Test on multiple screen sizes using @Preview(device = "spec:...")