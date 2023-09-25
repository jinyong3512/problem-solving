#include <iostream>
#include <vector>
using namespace std;

void dfs(vector<vector<int>>& graph, vector<bool>& visited, int index) {
    visited[index] = true;

    for (int i = 0; i < graph[index].size(); i++) {
        if (!visited[graph[index][i]]) {
            dfs(graph, visited, graph[index][i]);
        }
    }
}

int main() {
    int N, M;
    cin >> N >> M;

    vector<vector<int>> graph(N + 1);
    for (int i = 0; i <= N; i++)
        graph[i] = vector<int>();

    for (int i = 0; i < M; i++) {
        int v1, v2;
        cin >> v1 >> v2;
        graph[v2].push_back(v1);
    }

    vector<int> answer(N + 1);

    for (int i = 1; i <= N; i++) {
        vector<bool> visited(N + 1, false);
        dfs(graph, visited, i);

        for (int j = 1; j <= N; j++) {
            if (visited[j])
                answer[i]++;
        }
    }

    vector<int> answers;
    for (int i = 1; i < answer.size(); i++) {
        if (answers.empty())
            answers.push_back(i);
        else {
            if (answer[i] > answer[answers[0]]) {
                answers.clear();
                answers.push_back(i);
            } else if (answer[i] == answer[answers[0]]) {
                answers.push_back(i);
            } else {

            }
        }
    }

    for (int tmp : answers)
        cout << tmp << " ";

    return 0;
}
