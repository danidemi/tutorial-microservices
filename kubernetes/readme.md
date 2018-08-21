# Installing On Win

Using Chocolatey

    danidemi@WIN-DESK-BOX C:\Users\danidemi
    $ choco install kubernetes-cli
    Chocolatey v0.10.3
    Installing the following packages:
    kubernetes-cli
    By installing you accept licenses for the packages.
    
    kubernetes-cli v1.11.2 [Approved]
    kubernetes-cli package files install completed. Performing other installation steps.
    The package kubernetes-cli wants to run 'chocolateyInstall.ps1'.
    Note: If you don't run this script, the installation will fail.
    Note: To confirm automatically next time, use '-y' or consider setting
     'allowGlobalConfirmation'. Run 'choco feature -h' for more details.
    Do you want to run the script?([Y]es/[N]o/[P]rint): Y
    
    Extracting C:\ProgramData\chocolatey\lib\kubernetes-cli\tools\kubernetes-client-windows-386.tar.gz to C:\ProgramData\chocolatey\lib\kubernetes-cli\tools...
    C:\ProgramData\chocolatey\lib\kubernetes-cli\tools
    Extracting C:\ProgramData\chocolatey\lib\kubernetes-cli\tools\kubernetes-client-windows-386.tar to C:\ProgramData\chocolatey\lib\kubernetes-cli\tools...
    C:\ProgramData\chocolatey\lib\kubernetes-cli\tools
     ShimGen has successfully created a shim for kubectl.exe
     The install of kubernetes-cli was successful.
      Software installed to 'C:\ProgramData\chocolatey\lib\kubernetes-cli\tools'
    
    Chocolatey installed 1/1 packages. 0 packages failed.
     See the log for details (C:\ProgramData\chocolatey\logs\chocolatey.log).
     
Then...     
     
    $ kubectl version
    Client Version: version.Info{Major:"1", Minor:"11", GitVersion:"v1.11.2", GitCommit:"bb9ffb1654d4a729bb4cec18ff088eacc153c239", GitTreeState:"clean", BuildDate:"2018-08-07T23:17:28Z", GoVersion:"go1.10.3", Compiler:"gc", Platform:"windows/386"}
    Unable to connect to the server: dial tcp [::1]:8080: connectex: No connection could be made because the target machine actively refused it.

Then install minikube following info at <https://github.com/kubernetes/minikube/releases>. After installation...

    > minikube
    Minikube is a CLI tool that provisions and manages single-node Kubernetes clusters optimized for development workflows.
    
    Usage:
      minikube [command]
    
    Available Commands:
      addons           Modify minikube's kubernetes addons
      cache            Add or delete an image from the local cache.
      completion       Outputs minikube shell completion for the given shell (bash or zsh)
      config           Modify minikube config
      dashboard        Opens/displays the kubernetes dashboard URL for your local cluster
      delete           Deletes a local kubernetes cluster
      docker-env       Sets up docker env variables; similar to '$(docker-machine env)'
      get-k8s-versions Gets the list of Kubernetes versions available for minikube when using the localkube bootstrapper
      help             Help about any command
      ip               Retrieves the IP address of the running cluster
      logs             Gets the logs of the running localkube instance, used for debugging minikube, not user code
      mount            Mounts the specified directory into minikube
      profile          Profile sets the current minikube profile
      service          Gets the kubernetes URL(s) for the specified service in your local cluster
      ssh              Log into or run a command on a machine with SSH; similar to 'docker-machine ssh'
      ssh-key          Retrieve the ssh identity key path of the specified cluster
      start            Starts a local kubernetes cluster
      status           Gets the status of a local kubernetes cluster
      stop             Stops a running local kubernetes cluster
      update-check     Print current and latest version number
      update-context   Verify the IP address of the running cluster in kubeconfig.
      version          Print the version of minikube
    
    Flags:
          --alsologtostderr                  log to standard error as well as files
      -b, --bootstrapper string              The name of the cluster bootstrapper that will set up the kubernetes cluster. (default "kubeadm")
      -h, --help                             help for minikube
          --log_backtrace_at traceLocation   when logging hits line file:N, emit a stack trace (default :0)
          --log_dir string                   If non-empty, write log files in this directory
          --logtostderr                      log to standard error instead of files
      -p, --profile string                   The name of the minikube VM being used.
            This can be modified to allow for multiple minikube instances to be run independently (default "minikube")
          --stderrthreshold severity         logs at or above this threshold go to stderr (default 2)
      -v, --v Level                          log level for V logs
          --vmodule moduleSpec               comma-separated list of pattern=N settings for file-filtered logging
    
    Use "minikube [command] --help" for more information about a command.
    
Then let's prepare a single node kubernetes env

    > minikube start --vm-driver=virtualbox
    
This downloads a minikube `.iso` and does other stuffs.

    > minikube start --vm-driver=virtualbox
    Starting local Kubernetes v1.10.0 cluster...
    Starting VM...
    Downloading Minikube ISO
    160.27 MB / 160.27 MB [============================================] 100.00% 0s
    Getting VM IP address...
    Moving files into cluster...
    Downloading kubeadm v1.10.0
    Downloading kubelet v1.10.0
    Finished Downloading kubelet v1.10.0
    Finished Downloading kubeadm v1.10.0
    Setting up certs...
    Connecting to cluster...
    Setting up kubeconfig...
    Starting cluster components...
    Kubectl is now configured to use the cluster.
    Loading cached images from config file.
    
Now, let's follow <https://kubernetes.io/docs/setup/minikube/>
    
Let's run something 

    > kubectl run hello-minikube --image=k8s.gcr.io/echoserver:1.10 --port=8080
    deployment.apps/hello-minikube created
    
    danidemi@WIN-DESK-BOX C:\Users\danidemi
    >
    
    danidemi@WIN-DESK-BOX C:\Users\danidemi
    > kubectl expose deployment hello-minikube --type=NodePort
    service/hello-minikube exposed
    
 We have now launched an echoserver pod but we have to wait until the pod is up before curling/accessing it
via the exposed service.
To check whether the pod is up and running we can use the following:

    $ kubectl get pod
    NAME                              READY     STATUS              RESTARTS   AGE
    hello-minikube-3383150820-vctvh   0/1       ContainerCreating   0          3s
    
We can see that the pod is now Running and we will now be able to curl it:

    minikube service hello-minikube --url
    http://192.168.99.100:31953

    $ curl http://192.168.99.100:31953
    
    Hostname: hello-minikube-7c77b68cff-85k8c
    
    Pod Information:
            -no pod information available-
    
    Server values:
            server_version=nginx: 1.13.3 - lua: 10008
    
    Request Information:
            client_address=172.17.0.1
            method=GET
            real path=/
            query=
            request_version=1.1
            request_scheme=http
            request_uri=http://192.168.99.100:8080/
    
    Request Headers:
            accept=*/*
            host=192.168.99.100:31953
            user-agent=curl/7.55.1
    
    Request Body:
            -no body in request-
            
Then we can stop it            
            
    > kubectl delete services hello-minikube
    service "hello-minikube" deleted
    
    danidemi@WIN-DESK-BOX C:\Users\danidemi
    > kubectl delete deployment hello-minikube
    deployment.extensions "hello-minikube" deleted
    
## Dashboard

    > minikube dashboard    

## References

<https://kubernetes.io/docs/tasks/tools/install-kubectl/>