require 'calabash-android/calabash_steps'

Given(/^I am at "(.*?)" screen$/) do |expected_activity|
  actual_activity = performAction('get_activity_name')['message']
  raise "The current activity is #{actual_activity}" unless are_the_same?(actual_activity, expected_activity)
end


def are_the_same? actual_activity, expected_activity
	 actual_activity == expected_activity  ||  actual_activity == expected_activity + 'Activity' 
end