# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/yc/CLionProjects/AVLTree..

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/yc/CLionProjects/AVLTree../cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/AVLTree__.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/AVLTree__.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/AVLTree__.dir/flags.make

CMakeFiles/AVLTree__.dir/main.cpp.o: CMakeFiles/AVLTree__.dir/flags.make
CMakeFiles/AVLTree__.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/yc/CLionProjects/AVLTree../cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/AVLTree__.dir/main.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/AVLTree__.dir/main.cpp.o -c /Users/yc/CLionProjects/AVLTree../main.cpp

CMakeFiles/AVLTree__.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/AVLTree__.dir/main.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/yc/CLionProjects/AVLTree../main.cpp > CMakeFiles/AVLTree__.dir/main.cpp.i

CMakeFiles/AVLTree__.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/AVLTree__.dir/main.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/yc/CLionProjects/AVLTree../main.cpp -o CMakeFiles/AVLTree__.dir/main.cpp.s

# Object files for target AVLTree__
AVLTree___OBJECTS = \
"CMakeFiles/AVLTree__.dir/main.cpp.o"

# External object files for target AVLTree__
AVLTree___EXTERNAL_OBJECTS =

AVLTree__: CMakeFiles/AVLTree__.dir/main.cpp.o
AVLTree__: CMakeFiles/AVLTree__.dir/build.make
AVLTree__: CMakeFiles/AVLTree__.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/yc/CLionProjects/AVLTree../cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable AVLTree__"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/AVLTree__.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/AVLTree__.dir/build: AVLTree__

.PHONY : CMakeFiles/AVLTree__.dir/build

CMakeFiles/AVLTree__.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/AVLTree__.dir/cmake_clean.cmake
.PHONY : CMakeFiles/AVLTree__.dir/clean

CMakeFiles/AVLTree__.dir/depend:
	cd /Users/yc/CLionProjects/AVLTree../cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/yc/CLionProjects/AVLTree.. /Users/yc/CLionProjects/AVLTree.. /Users/yc/CLionProjects/AVLTree../cmake-build-debug /Users/yc/CLionProjects/AVLTree../cmake-build-debug /Users/yc/CLionProjects/AVLTree../cmake-build-debug/CMakeFiles/AVLTree__.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/AVLTree__.dir/depend

