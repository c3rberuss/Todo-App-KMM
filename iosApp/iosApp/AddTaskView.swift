//
//  AddTaskView.swift
//  iosApp
//
//  Created by Josue on 11/23/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AddTaskView: View {
    @ObservedObject var viewModel: TodoViewModel
    @Environment(\.presentationMode) var mode: Binding<PresentationMode>
    
    var body: some View{
        
        VStack{
            
            TextField("Task name", text: $viewModel.taskName)
                .padding(.all, 8)
                .border(.gray)
                .cornerRadius(16, antialiased: true)
            TextField("Task description", text: $viewModel.taskDescription)
                .padding(.all, 8)
                .border(.gray)
                .cornerRadius(16, antialiased: true)
            
            Spacer()
            Button("Add task"){
                
                let task = Task(id: 0, name: viewModel.taskName, description: viewModel.taskDescription, status: .pending, position: 1)
                viewModel.saveTask(task: task)
                
                self.mode.wrappedValue.dismiss()
            }
            Spacer()
            
        }
        .padding(.all, 16)
    
    }
}

struct AddTaskView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = TodoViewModel()
        AddTaskView(viewModel: viewModel)
    }
}
