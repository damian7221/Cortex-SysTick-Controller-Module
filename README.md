### Cortex-SysTick-Controller-Module

#### Introduction:
The Cortex-SysTick-Controller-Module project is a Java-based graphical user interface application aimed at simulating the functionality of the SysTick controller module commonly found in microcontroller systems.

#### Project Components:

1. **Pulse Generator (Generator Class):**
   - Responsible for generating pulses at specified intervals.
   - Implements the PulseSource interface, enabling its use as a pulse source for other components.
   - Key functions include controlling pulse intervals and starting/stopping the generator.

2. **Rotating Wheel Interface (ObracajaceKolo Class):**
   - Offers an intuitive interface for users to interactively adjust values by rotating a virtual wheel.
   - Transforms rotation angles into corresponding values and displays them to users.
   - Allows setting a maximum value limit for the rotation.

3. **Application Window (Okienko Class):**
   - Main graphical interface window integrating all project components.
   - Enables users to configure values for the RVR, CSR, and CVR registers of the SysTick controller module.
   - Features buttons to initiate and halt the pulse generator and text fields to display outputs and set pulse delays.

4. **SysTick Controller Module (Systick Class):**
   - Simulates the behavior of the SysTick controller module present in microcontroller systems.
   - Allows manipulation of RVR and CVR register values, control over the operating mode, and activation/deactivation of the SysTick module.
   - Simulates both internal and external SysTick clock events.

#### Setup and Execution:

- **Execution**: Run the `Okienko.java` file to launch the application.
- **Files**:
  - `Generator.java`: Handles pulse generation and interface with other components.
  - `ObracajaceKolo.java`: Implements the rotating wheel interface for value adjustment.
  - `Okienko.java`: Main application window integrating all components.
  - `Systick.java`: Simulates the SysTick controller module's behavior.

#### Author:

- **Author**: Damian Łojko
- The application was created as part of Java programming classes at the university.
