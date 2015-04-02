function FeedCtrl($scope, $location,FeedService,ProfileService) {

    $scope.feedTwitter={};
    $scope.feedLinkedin={};
    $scope.feedFacebook={};

    ProfileService.findProfileImageFacebook().success(function (response) {
        $scope.profileImage=response;
    });

    ProfileService.friendsProfile().success(function (response) {
        $scope.friends=response;
    });

    FeedService.findFeedTwitter().
        success(function (users) {
            $scope.feedTwitter = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });

    FeedService.findFeedFacebook().
        success(function (users) {
            $scope.feedFacebook = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });

    FeedService.findFeedLinkedin().
        success(function (users) {
            $scope.feedLinkedin = users;
        })
        .error(function (resp) {
            console.log("Error with FriendsService.findFriendsTwitter" + resp);
        });



}