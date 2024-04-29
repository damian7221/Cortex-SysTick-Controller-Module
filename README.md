### Cortex-SysTick-Controller-Module Project Description

This project consists of several components that together create a graphical user interface application in Java.

#### Project Components:

1. **Pulse Generator (Generator Class):**
   - A thread responsible for generating pulses at specified intervals.
   - Implements the PulseSource interface, enabling its use as a pulse source for other components.
   - Provides methods for controlling the intervals between pulses and starting/stopping the generator.

2. **Rotating Wheel Interface (ObracajaceKolo Class):**
   - A panel allowing users to interactively change values by rotating a wheel.
   - The value is transformed from the rotation angle to the corresponding value and displayed to the user.
   - Also allows setting a maximum value that can be achieved by rotating the wheel.

3. **Application Window (Okienko Class):**
   - The main graphical interface window integrating all project components.
   - Allows users to set values for the RVR, CSR, and CVR registers of the SysTick controller module.
   - Contains buttons to start and stop the pulse generator and text fields to display results and set pulse delays.

4. **SysTick Controller Module (Systick Class):**
   - Simulates the operation of the SysTick controller module, commonly used in microcontroller systems.
   - Allows setting values for the RVR and CVR registers, controlling the operating mode, and activating/deactivating the SysTick module.
   - Simulates SysTick clock events, both internal and external.

#### Project Features:

- Users can experiment with various SysTick module parameters, such as pulse intervals, register values, and operating modes.
- The Rotating Wheel Interface enables intuitive manipulation of values by interacting with a rotating wheel.
- The application provides monitoring of results by displaying current register values and pulse generator outputs.
