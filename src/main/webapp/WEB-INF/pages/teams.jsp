<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sports Schedule</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<style>
	.header{
		height: 80px;

		border-bottom: solid 1px grey;
	}

	.header-list{
		list-style-type: none;
	}

	.header-list li{
		float: left;

		padding: 0px 10px;
	}

	.team-wrapper{
		padding: 30px;
	}

	.team-item{
		-webkit-box-shadow: 10px 10px 50px 0px rgba(0,0,0,0.75);
		-moz-box-shadow: 10px 10px 50px 0px rgba(0,0,0,0.75);
		box-shadow: 10px 10px 50px 0px rgba(0,0,0,0.75);
	}

	.team-logo{
		text-align: center;
	}

	.team-name{
		text-align: center;
	}

</style>

<body>

	<div ng-app="teamApp">
		<div ng-controller="teamController as tc">

			<div class="header">
				<ul class="header-list">
					<li>Teams</li>
					<li>Favorites</li>
					<li ng-click="changeLeague('NFL')">NFL</li>
					<li ng-click="changeLeague('MLB')">MLB</li>
				</ul>
			</div>

			<div class="team-list">
				<div class="row">
					<div ng-repeat="team in tc.teams">
						<div class="team-wrapper col-md-3">

							<div class="team-item" ng-click="teamClick(team)">
								<div id="secondary-border" ng-style="{'border-style': 'solid', 'border-width': '3px', 'border-color': '{{team.secondaryColor}}'}">
									<div id="primary-border" ng-style="{'border-style': 'solid', 'border-width': '3px', 'border-color': '{{team.primaryColor}}'}">
										<div class="team-logo">
											<img ng-src="{{team.logoURL}}"/>
										</div>

										<div class="team-name">
											<p>{{team.city}}</p>
											<p>{{team.mascot}}</p>
											<%--<p>{{team.currentOpponent}}</p>--%>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>


</body>
</html>

<script src='<c:url value="/resources/js/day_hours.js" />' ></script>
<script src='<c:url value="/resources/js/nfl_game_stats-2.0.2.js" />' ></script>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"></script>
<script>

	var teamApp = angular.module('teamApp', []);

	teamApp.factory('teamFactory', ['$http', function($http) {

		var teamFactory = {};

		teamFactory.getAllTeams = function (league) {
			return $http({
				url: '/getAllTeams',
				method: 'GET',
				params: {league: league}
			});
		};

		/*teamFactory.getSchedules = function (seasonNum, seasonType, week){

			return $http.get('http://www.nfl.com/ajax/scorestrip?season='+seasonNum+'&seasonType='+seasonType+'&week='+week);

		};*/

		return teamFactory;

	}]);

	teamApp.controller('teamController', ['$scope', 'teamFactory',
		function ($scope, teamFactory) {

			console.log('begin');

			var self = this;
			self.teams = [];

			getTeams('NFL');

			//self.currentSchedule = [];

			function getTeams(league) {
				teamFactory.getAllTeams(league)
						.success(function (teams) {
							self.teams = {};
							self.teams = teams;
							console.log(self.teams);
							//getSchedules();
						})
						.error(function (error) {
							console.log(error);
							console.log('Error Loading All Teams');
						});
			}

			$scope.changeLeague = function(league){
				console.log('Changing to ' + league);
				getTeams(league);
			}

			/*function getSchedules(){
				teamFactory.getSchedules(2015, 'REG', 7)
						.success(function(schedule){
							self.currentSchedule = getWeekGameStats(schedule);
							console.log(self.currentSchedule);
							self.teams.forEach(appendSchedule);
						})
						.error(function(error){
							console.log(error);
							console.log("Error Loading Schedule");
						});
			}

			function appendSchedule(value, index, ar){

				console.log('Team: ' + value.nflAbbr);

				for(var i=0; i<self.currentSchedule.gameStats.length; i++) {

					if(self.currentSchedule.gameStats[i].homeTeam.abbrCityname ==  value.nflAbbr){
						value.currentOpponent = self.currentSchedule.gameStats[i].awayTeam.abbrCityname;
						console.log('Playing: ' + self.currentSchedule.gameStats[i].awayTeam.abbrCityname);
						break;
					} else if(self.currentSchedule.gameStats[i].awayTeam.abbrCityname == value.nflAbbr){
						value.currentOpponent = self.currentSchedule.gameStats[i].homeTeam.abbrCityname;
						console.log('Playing: ' + self.currentSchedule.gameStats[i].homeTeam.abbrCityname);
						break;
					}

					value.currentOpponent = 'BYE';
					console.log('Playing: BYE');

				}

			}*/

			$scope.teamClick = function(team){
				console.log(team);
			}

		}]);


</script>