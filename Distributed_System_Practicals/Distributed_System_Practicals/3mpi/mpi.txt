
#include <mpi.h>
#include <stdio.h>

int main(int argc, char** argv) {
    int rank;
    MPI_Init(&argc, &argv);                  // Initialize MPI
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);   // Get process rank

    if (rank == 0) {
        int message = 42;
        MPI_Send(&message, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
        printf("Process 0 sent message: %d\n", message);
    } else if (rank == 1) {
        int received;
        MPI_Recv(&received, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        printf("Process 1 received message: %d\n", received);
    }

    MPI_Finalize(); // Finalize MPI
    return 0;
}
