import SwiftUI
import shared

struct ContentView: View {
	let greet = "Hello :)"
    
    @StateObject var viewModel = TodoViewModel()

	var body: some View {
        NavigationView{
            List{
                
                ForEach(viewModel.todoList, id: \.id){ task in
                    Text(task.name)
                }.onDelete(perform: viewModel.deleteTask)
                
            }
            .onAppear{
                viewModel.getAllTasks()
            }
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


final class TodoViewModel: ObservableObject{
    
    private var todoSDK: TodoAppSDK
    
    @Published var todoList: [Task] = []
    
    @Published var taskName: String = ""
    @Published var taskDescription: String = ""
    
    init() {
        self.todoSDK = TodoAppSDK(databaseDriverFactory: DatabaseDriverFactory())
    }
    
    func getAllTasks(){
        todoSDK.getAllTasks(completionHandler: { tasks, _ in
            if tasks != nil{
                self.todoList = tasks!
            }
        })
    }
    
    func saveTask(task: Task){
        todoSDK.saveTask(task: task, completionHandler: {error in
            if error == nil{
                self.getAllTasks()
            }
        })
    }
    
    func deleteTask(at offset: IndexSet){
        
        let taskToDelete = offset.map{ self.todoList[$0].id }.first
        
        todoSDK.deleteTask(taskId: taskToDelete!, completionHandler: { error in
            if error == nil{
                self.getAllTasks()
            }
        })
    }
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
