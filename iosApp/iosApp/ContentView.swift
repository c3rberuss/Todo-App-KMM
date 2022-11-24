import SwiftUI
import shared

struct ContentView: View {
	let greet = "Hello :)"
    
    @StateObject var viewModel = TodoViewModel()
    

	var body: some View {
        NavigationView{
            
            VStack{
                
                if viewModel.state is TodoAppStateLoadingTasks{
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle())
                    
                } else if viewModel.state is TodoAppStateShowAllTask{
                    List{
                        ForEach(viewModel.todoList, id: \.id){ task in
                            Text(task.name)
                        }.onDelete(perform: viewModel.deleteTask)
                    }
                }else if viewModel.state is TodoAppStateEmptyTasks{
                    Text("No tasks availables")
                }
                
                Spacer()
                    .navigationBarTitle("Todo App")
                    .toolbar{
                        ToolbarItem(placement: .navigationBarTrailing){
                            NavigationLink("Add new task"){
                                AddTaskView(viewModel: viewModel)
                            }
                        }
                    }
                
            }
        }
	}
}


final class TodoViewModel: ObservableObject{
    
    private var todoSDK: TodoAppSDK
    
    @Published var todoList: [Task] = []
    @Published var state: TodoAppState = TodoAppStateInitial()
    
    @Published var taskName: String = ""
    @Published var taskDescription: String = ""
    
    init() {
        self.todoSDK = TodoAppSDK(databaseDriverFactory: DatabaseDriverFactory())
        
        self.todoSDK.store.watchState().watch{ state in
            self.state = state

            print("App state \(state)")

            if state is TodoAppStateInitial{
                self.getAllTasks()
            }else if state is TodoAppStateShowAllTask{
                self.todoList = (state as? TodoAppStateShowAllTask)!.tasks
            }else if state is TodoAppStateEmptyTasks{
                self.todoList = []
            }
        }
    }
    
    func getAllTasks(){
        todoSDK.store.dispatch(action: TodoActionLoadTasks()) { error in
            if error != nil{
                print("Something went wrong loading tasks \(error)")
            }
        }
    }
    
    func saveTask(task: Task){
        todoSDK.store.dispatch(action: TodoActionSaveTask(task: task)) { error in
            
        }
    }
    
    func deleteTask(at offset: IndexSet){
        
        let taskToDelete = offset.map{ self.todoList[$0].id }.first
        
        todoSDK.store.dispatch(action: TodoActionDeleteTask(taskId: taskToDelete!)) { error in
            
        }
        
    }
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
