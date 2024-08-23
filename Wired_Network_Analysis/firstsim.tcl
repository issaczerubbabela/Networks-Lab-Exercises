# Create a simulator object
set ns [new Simulator]

# Create a trace file for logging
set tracefile [open firstsim.tr w]
$ns trace-all $tracefile

# Create a NAM file for animation
set namfile [open firstsim.nam w]
$ns namtrace-all $namfile

# Create nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

# Create links between nodes with Drop Tail Queue
$ns duplex-link $n0 $n2 5Mb 2ms DropTail
$ns duplex-link $n1 $n2 10Mb 5ms DropTail
$ns duplex-link $n2 $n3 4Mb 3ms DropTail
$ns duplex-link $n3 $n4 100Mb 2ms DropTail
$ns duplex-link $n3 $n5 15Mb 4ms DropTail

# Create agents (Node 0 to Node 4)
set udp [new Agent/UDP]
set null [new Agent/Null]
$ns attach-agent $n0 $udp
$ns attach-agent $n4 $null
$ns connect $udp $null

# Create TCP agents
set tcp [new Agent/TCP]
set sink [new Agent/TCPSink]
$ns attach-agent $n1 $tcp
$ns attach-agent $n5 $sink
$ns connect $tcp $sink

# Create CBR and FTP applications
set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp
set ftp [new Application/FTP]
$ftp attach-agent $tcp

# Start the traffic
$ns at 1.0 "$cbr start"
$ns at 2.0 "$ftp start"

# Define the finish procedure
$ns at 10.0 "finish"
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exit 0
}

puts "Simulation is starting..."
$ns run
